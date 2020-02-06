package cn.imooc.ui_button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends Activity implements View.OnClickListener {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        btn = (Button) findViewById(R.id.btn01);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当按钮被点击时调用
        Toast.makeText(ThirdActivity.this, "按钮已被点击（Activity直接实现接口）", Toast.LENGTH_SHORT)
                .show();
    }
}
