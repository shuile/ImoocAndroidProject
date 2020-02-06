package cn.imooc.activity01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为当前的Activity设置布局内容
        setContentView(R.layout.activity_main);
    }
}
