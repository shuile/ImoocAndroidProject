package cn.shui.cameratest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final int REQUEST_CODE = 0;

    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/imooc/temp.jpg";
    public static final int REQUEST_CODE_CAMERA = 1;
    private ImageView mImageview;
    private FrameLayout mFrameLayout;
    private Camera mCamera;
    private CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageview = findViewById(R.id.image_view);
        mFrameLayout = findViewById(R.id.camera_layout);

        intPermission();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraView != null) {
            mCameraView.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void intPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, WRITE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{WRITE, CAMERA}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length != 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_CAMERA == requestCode) {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                mImageview.setImageURI(Uri.fromFile(file));
            }
        }
    }

    public void systemCamera(View view) {

        if (mCameraView != null) {
            mCameraView.release();
        }

        // open system camera.
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }

        // 文件转换成uri
        Uri uri = getImageUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, REQUEST_CODE_CAMERA);

    }

    private Uri getImageUri() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public void cameraAPI(View view) {
        // 创建一个camera视图。
        // 将camera视图添加到cameraLayout里。
        // 做一些camera的设置。
        mCamera = getCamera();
        mCameraView = new CameraView(this, mCamera);
        mFrameLayout.addView(mCameraView);

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setRotation(90);
        mCamera.setParameters(parameters);
    }

    public void takePhoto(View view) {
        // 点击按钮拍照，并保存到存储里。
        if (mCamera != null) {
            mCamera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    File file = new File(FILE_PATH);
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(data);
                        fileOutputStream.flush();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public Camera getCamera() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
            // 错误提示
        }
        return camera;
    }

    public static class CameraView extends SurfaceView implements SurfaceHolder.Callback {

        private Camera mCamera;
        private final SurfaceHolder mHolder;

        public CameraView(Context context, Camera camera) {
            super(context);
            mCamera = camera;
            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (mHolder.getSurface() == null) {
                return;
            }

            if (mCamera != null) {
                mCamera.stopPreview();
            }

            try {
                if (mCamera != null) {
                    mCamera.setPreviewDisplay(holder);
                    mCamera.startPreview();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            release();
        }

        public void release() {
            if (mCamera != null) {
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        }
    }
}
