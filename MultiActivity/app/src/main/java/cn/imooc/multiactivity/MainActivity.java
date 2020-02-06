package cn.imooc.multiactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startOther = (Button) findViewById(R.id.btn);
        startOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击事件触发后执行，启动OtherActivity
                //创建一个Intent对象
                Intent intent = new Intent();
                //调用setClass方法指定启动某一个Activity
                intent.setClass(MainActivity.this, OtherActivity.class);
                //调用startActivity
                startActivity(intent);
            }
        });
    }
}
