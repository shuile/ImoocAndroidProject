package com.shui.storagedemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ShareActivity extends AppCompatActivity {

    private EditText accEdt;
    private EditText pwdEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        accEdt  =(EditText) findViewById(R.id.acc_edt);
        pwdEdt  =(EditText) findViewById(R.id.pwd_edt);

        //SharedPreference的读取
        //①获取SharedPreference对象（参数1：文件名  参数2：模式）
        SharedPreferences share = getSharedPreferences("myshare", MODE_PRIVATE);
        //②根据key获取内容(参数1：key  参数2：当对应key不存在时，返回参数2的内容作为默认值)
        String accStr = share.getString("account", "");
        String pwdStr = share.getString("pwd", "");

        accEdt.setText(accStr);
        pwdEdt.setText(pwdStr);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.获取两个输入框的内容
                String account = accEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                //2.验证(admin 123)
                if (account.equals("admin") && pwd.equals("123")) {
                    //2.1存储信息到SharedPreference
                    //①获取SharedPreference对象（参数1：文件名  参数2：模式）
                    SharedPreferences share = getSharedPreferences("myshare", MODE_PRIVATE);
                    //②获取Editor对象
                    SharedPreferences.Editor edt = share.edit();
                    //③存储信息
                    edt.putString("account", account);
                    edt.putString("pwd", pwd);
                    //④指定提交操作
                    edt.commit();

                    Toast.makeText(ShareActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                } else {
//                    2.2验证失败，提示用户
                    Toast.makeText(ShareActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
