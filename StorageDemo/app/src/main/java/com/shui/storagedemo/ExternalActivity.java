package com.shui.storagedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalActivity extends AppCompatActivity {

    private static final String TAG = "ExternalActivity";
    public static final int WESCODE = 123;

    private EditText infoEdt;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        infoEdt = (EditText) findViewById(R.id.info_edt);
        txt = (TextView) findViewById(R.id.textView);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //动态去申请权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WESCODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WESCODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WESCODE);
                break;
            default:
                break;
        }
    }

    public void operate(View view) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "imooc.txt";
        Log.e(TAG, "operate: " + path);
//        if (Environment.getExternalStorageState().equals("mounted"))
        switch (view.getId()) {
            case R.id.save_btn:
                File file = new File(path);
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(path, true);
                    String str = infoEdt.getText().toString();
                    fos.write(str.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.read_btn:
                try {
                    FileInputStream fis = new FileInputStream(path);
                    byte[] buffer = new byte[1024];
                    int len = fis.read(buffer);
                    String str2 = new String(buffer, 0, len);
                    txt.setText(str2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
