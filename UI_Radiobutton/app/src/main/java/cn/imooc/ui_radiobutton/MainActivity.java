package cn.imooc.ui_radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);

        rg = (RadioGroup) findViewById(R.id.rg_sex);
        rbMale = (RadioButton) findViewById(R.id.rb_male);
        rbFemale = (RadioButton) findViewById(R.id.rb_female);
        rg.setOnCheckedChangeListener(new MyRadioButtonListener());
    }

    class MyRadioButtonListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //选中状态改变时被触发
            switch (checkedId) {
                case R.id.rb_male:
                    //当用户选择男性时
                    Log.i("sex", "当前用户选择：" + rbMale.getText().toString().trim());
                    break;
                case R.id.rb_female:
                    //当前用户选择女性时
                    Log.i("sex", "当前用户选择：" + rbFemale.getText().toString().trim());
            }
        }
    }
}
