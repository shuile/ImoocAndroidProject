package cn.shui.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String MY_ACTION = "com.shui.demo.dfghjk";
    public static final String BROADCAST_CONTENT = "broadcast_content";
    private ImoocBroadcastReceiver broadcastReceiver;
    private EditText mInputEditText;
    private Button mSendBroadcastButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置应用主页面的标题
        setTitle(getPackageName());

        mInputEditText = findViewById(R.id.inputEditText);
        mSendBroadcastButton = findViewById(R.id.sendBroadcastButton);
        mResultTextView = findViewById(R.id.resultTextView);

        // 新建一个广播接收器
        broadcastReceiver = new ImoocBroadcastReceiver(mResultTextView);

        // 要接收哪些广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(MY_ACTION);

        // 注册广播接收器
        registerReceiver(broadcastReceiver, intentFilter);

        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新建广播
                Intent intent = new Intent(MY_ACTION);
                // 放入广播要携带的数据
                intent.putExtra(BROADCAST_CONTENT, mInputEditText.getText().toString().trim());
                sendBroadcast(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册广播接收器
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
