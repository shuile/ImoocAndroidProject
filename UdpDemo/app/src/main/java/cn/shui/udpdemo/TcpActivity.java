package cn.shui.udpdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.shui.udpdemo.biz.TcpClientBiz;

public class TcpActivity extends AppCompatActivity {

    private static final String TAG = "TcpActivity";

    private EditText mEtMsg;
    private Button mBtnSend;
    private TextView mTvContent;

    private TcpClientBiz mTcpClientBiz = new TcpClientBiz();

    private StringBuilder mContentSb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mTcpClientBiz.setOnMsgComingListener(new TcpClientBiz.OnMsgComingListener() {
            @Override
            public void onMsgComing(String msg) {
                appendMsgToContent(msg);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initViews() {
        mEtMsg = findViewById(R.id.et_msg);
        mBtnSend = findViewById(R.id.btn_send);
        mTvContent = findViewById(R.id.tv_content);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mEtMsg.getText().toString().trim();
                if (TextUtils.isEmpty(msg)) {
                    return;
                }
                mTvContent.setText("");
                mTcpClientBiz.sendMsg(msg);
            }
        });
    }

    private void appendMsgToContent(String msg) {

        mTvContent.append(msg + "\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTcpClientBiz.onDestroy();
    }
}
