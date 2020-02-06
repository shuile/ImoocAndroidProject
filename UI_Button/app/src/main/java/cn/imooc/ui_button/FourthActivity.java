package cn.imooc.ui_button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FourthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
    }

    public void MyButtonClick(View v) {
        //将在btn01按钮被点击时调用
        Toast.makeText(FourthActivity.this, "按钮已被点击（xml布局文件中定义）", Toast.LENGTH_SHORT)
                .show();
    }
}
