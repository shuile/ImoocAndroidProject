package cn.imooc.ui_button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends Activity {

    private Button btn;

    /*
     *第一步：获取Button对象
     * 第二步：直接调用button的setOnClickListener方法
     * a，在方法中，传入newOnClickListener方法
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        btn = (Button) findViewById(R.id.btn01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "按钮被点击了！！！(匿名内部类)", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
