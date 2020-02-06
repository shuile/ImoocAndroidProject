package cn.imooc.imoocdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//创建MainActivity类集成自AppComatActivity（Activity？界面类！）
public class MainActivity extends AppCompatActivity {

    @Override
    //重写Activity类的onCreate方法，将在当前界面创建时被系统调用
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容视图（R.layout.activity_main）
        setContentView(R.layout.activity_main);
    }
}
