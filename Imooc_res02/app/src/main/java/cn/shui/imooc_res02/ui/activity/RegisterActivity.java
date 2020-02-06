package cn.shui.imooc_res02.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.shui.imooc_res02.R;
import cn.shui.imooc_res02.UserInfoHolder;
import cn.shui.imooc_res02.bean.User;
import cn.shui.imooc_res02.biz.UserBiz;
import cn.shui.imooc_res02.net.CommonCallback;
import cn.shui.imooc_res02.util.T;

public class RegisterActivity extends BaseActivity {

    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtRepassword;
    private Button mBtnRegister;

    private UserBiz mUserBiz = new UserBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpToolbar();

        initView();

        initEvent();

        setTitle("注册");
    }

    private void initView() {
        mEtUsername = findViewById(R.id.id_et_username);
        mEtPassword = findViewById(R.id.id_et_password);
        mEtRepassword = findViewById(R.id.id_et_repassword);
        mBtnRegister = findViewById(R.id.id_btn_register);
    }

    private void initEvent() {
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                String repassword = mEtRepassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    T.showToast("账号或密码不能为空");
                    return;
                }

                if (!password.equals(repassword)) {
                    T.showToast("两次输入的密码不一致");
                    return;
                }

                startLoadingProgress();

                mUserBiz.register(username, password, new CommonCallback<User>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                    }

                    @Override
                    public void onSuccess(User response) {
                        stopLoadingProgress();
                        T.showToast("注册成功, 用户名为：" + response.getUsername());
                        LoginActivity.launch(RegisterActivity.this, response.getUsername(),
                                response.getPassword());
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserBiz.onDestroy();
    }
}
