package cn.shui.handledemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-vv";

    private Button mBtnButton;
    private TextView mTvMsg;

    private Handler mMainHandler;
    private Handler mChildHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnButton = findViewById(R.id.btn_button);
        mTvMsg = findViewById(R.id.tv_msg);

        mMainHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String fromChildString = (String) msg.obj;
                mTvMsg.setText(fromChildString);
            }
        };

        mBtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChildHandler == null) {
                    return;
                }
                Message message = new Message();
                message.what = 9;
                message.obj = "Hello";
                mChildHandler.sendMessage(message);
            }
        });
        new ChildThread().start();
    }

    private class ChildThread extends Thread {


        @Override
        public void run() {
            super.run();
            Looper.prepare();

            mChildHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    String fromMainString = (String) msg.obj;
                    String toMainString = fromMainString + ", world!";
                    Log.d(TAG, "handleMessage:接收到主线程发过来的what为 " + msg.what);
                    if (mMainHandler == null) {
                        return;
                    }
                    Message message = new Message();
                    message.obj = toMainString;
                    mMainHandler.sendMessage(message);
                }
            };

            Looper.loop();
        }
    }
}
