package cn.shui.udpdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.shui.udpdemo.biz.TcpClientBiz;
import cn.shui.udpdemo.https.HttpUtils;
import cn.shui.udpdemo.https.HttpUtils2;

public class HttpActivity extends AppCompatActivity {

    private static final String TAG = "TcpActivity";

    private EditText mEtMsg;
    private Button mBtnSend;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        mEtMsg = findViewById(R.id.et_msg);
        mBtnSend = findViewById(R.id.btn_send);
        mTvContent = findViewById(R.id.tv_content);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mEtMsg.getText().toString().trim();
                HttpUtils2.doGet(getApplicationContext(), url, new HttpUtils2.HttpListener() {
                    @Override
                    public void onSuccess(String content) {
                        mTvContent.setText(content);
                    }

                    @Override
                    public void onFail(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
