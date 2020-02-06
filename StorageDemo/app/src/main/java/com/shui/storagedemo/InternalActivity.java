package com.shui.storagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity {

    private EditText edt;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        edt = (EditText) findViewById(R.id.editText);
        txt = (TextView) findViewById(R.id.textView);
    }

    public void operate(View view) {
        //  data/data/包名/files
        //  getCacheDir()    data/data/包名/cache
        File file = new File(getFilesDir(), "getFileDir.txt");
        switch (view.getId()) {
            case R.id.save_btn:
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(edt.getText().toString().getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.read_btn:
                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int length = fis.read(buffer);
                    String str2 = new String(buffer, 0, length);
                    txt.setText(str2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
