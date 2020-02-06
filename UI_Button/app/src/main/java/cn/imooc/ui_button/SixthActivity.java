package cn.imooc.ui_button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SixthActivity extends Activity {

    private Button btn01;
    private Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        ButtonListener buttonListener = new ButtonListener();
        btn01.setOnClickListener(buttonListener);
        btn02.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn01:
                    Toast.makeText(SixthActivity.this, "已点击第一个按钮", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.btn02:
                    Toast.makeText(SixthActivity.this, "已点击第二个按钮", Toast.LENGTH_SHORT)
                            .show();
                    break;
                default:
                    break;
            }
        }
    }
}
