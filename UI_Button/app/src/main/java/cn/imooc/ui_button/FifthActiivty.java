package cn.imooc.ui_button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FifthActiivty extends Activity  {

    private Button btn01;
    private Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);
        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FifthActiivty.this, "点击了第一个按钮", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FifthActiivty.this, "点击了第二个按钮", Toast.LENGTH_SHORT)
                        .show();
            }
        });
//        btn01.setOnClickListener(this);
//        btn02.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn01:
////                Toast.makeText(FifthActiivty.this, "", Toast.LENGTH_SHORT)
////                        .show();
//                break;
//            case R.id.btn02:
////                Toast.makeText(FifthActiivty.this, "", Toast.LENGTH_SHORT)
////                        .show();
//                break;
//            default:
//                break;
//        }
//    }
}
