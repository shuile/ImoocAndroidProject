package cn.shui.udpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.shui.udpdemo.R;
import cn.shui.udpdemo.biz.UdpClientBiz;

public class UdpActivity extends AppCompatActivity {

    private static final String TAG = "UdpActivity";

    private EditText mEtMsg;
    private Button mBtnSend;
    private TextView mTvContent;

    private UdpClientBiz mUdpClientBiz = new UdpClientBiz();

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
                String msg = mEtMsg.getText().toString().trim();
                if (TextUtils.isEmpty(msg)) {
                    return;
                }

                appendMsgToContent("client:" + msg);
                mTvContent.setText("");
                mUdpClientBiz.sendMsg(msg, new UdpClientBiz.OnMsgReturnedListener() {
                    @Override
                    public void onMsgReturned(String msg) {
                        appendMsgToContent("server:" + msg);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }

    private void appendMsgToContent(String msg) {

        mTvContent.append(msg + "\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUdpClientBiz.onDestroy();
    }
}
