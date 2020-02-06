package cn.imooc.imoochomework_android06.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.imooc.imoochomework_android06.R;
import cn.imooc.imoochomework_android06.util.Constant;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText mLoginUserEt;
    private EditText mPwdEt;
    private Button mLoginBtn;
    private Button mFindPwdBtn;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化视图
        initView();
    }

    private void initView() {
        mLoginUserEt = (EditText) findViewById(R.id.loginuser_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mFindPwdBtn = (Button) findViewById(R.id.find_pwd_btn);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);

        mLoginBtn.setOnClickListener(this);
        mFindPwdBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                String username = mLoginUserEt.getText().toString().trim();
                String pwd = mPwdEt.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和登录密码", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.find_pwd_btn:

                break;
            case R.id.register_btn:
                startActivityForResult(
                        new Intent(LoginActivity.this, RegisterActivity.class),
                        Constant.REQUESTCODE_LOGIN_TO_REGISTER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUESTCODE_LOGIN_TO_REGISTER:
                if (resultCode != RESULT_OK) {
                    return;
                }
                if (data == null) {
                    return;
                }
                mLoginUserEt.setText(data.getStringExtra("username"));
        }
    }
}
