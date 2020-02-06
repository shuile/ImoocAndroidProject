package com.shui.storagedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operate(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.share_btn:
                intent = new Intent(this, ShareActivity.class);
                break;
            case R.id.external_btn:
                intent = new Intent(this, ExternalActivity.class);
                break;
            case R.id.internal_btn:
                intent = new Intent(this, InternalActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
