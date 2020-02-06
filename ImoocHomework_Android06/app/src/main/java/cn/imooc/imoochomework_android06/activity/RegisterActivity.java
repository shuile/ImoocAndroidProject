package cn.imooc.imoochomework_android06.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.imooc.imoochomework_android06.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mRegisterUsernameEt;
    private EditText mRegisterPwdEt;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //初始化视图
        initView();
    }

    private void initView() {
        mRegisterUsernameEt = (EditText) findViewById(R.id.register_username_et);
        mRegisterPwdEt = (EditText) findViewById(R.id.register_pwd_et);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);

        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                String username = mRegisterUsernameEt.getText().toString().trim();
                String pwd = mRegisterPwdEt.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("username", username);
                    setResult(RESULT_OK, intent);
                    finish();
                }
        }
    }
}
