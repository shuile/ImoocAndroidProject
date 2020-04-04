package cn.shui.videotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_STORAGE = 1001;
    private ListView mListView;
    private List<String> mDatas = Arrays.asList("Use Intent", "Use VideoView",
            "Use MediaPlayer & SurfaceView");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_view);

        mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_main,
                R.id.text_view, mDatas));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        checkPermissionAndPlayVideo();
                        break;
                    case 1:
                        VideoViewActivity.start(MainActivity.this);
                        break;
                    case 2:
                        MediaPlayerActivity.start(MainActivity.this);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void checkPermissionAndPlayVideo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_CODE_STORAGE);
        } else {
            playVideoUseIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CODE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    playVideoUseIntent();
                } else {
                    Toast.makeText(this, "该功能需要SDCard权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void playVideoUseIntent() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "lalala.mp4");
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= 24) {
            Uri contentUri = FileProvider.getUriForFile(this, "cn.shui.videotest.fileprovider", file);
            intent.setDataAndType(contentUri, "video/*");

            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intent.setDataAndType(Uri.fromFile(file), "video/*");
        }
        startActivity(intent);
    }
}
