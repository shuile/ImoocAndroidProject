package cn.shui.imooc_res02.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import cn.shui.imooc_res02.R;

public class SplashActivity extends AppCompatActivity {

    private Button mBtnSkip;
    private Handler mHandler = new Handler();

    private Runnable mRunnableRoLogin = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        initEvent();

        mHandler.postDelayed(mRunnableRoLogin, 3 * 1000);
    }

    private void initEvent() {
        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(mRunnableRoLogin);
                toLoginActivity();
            }
        });
    }

    private void initView() {
        mBtnSkip = findViewById(R.id.id_btn_skip);
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnableRoLogin);
    }
}
