package cn.imooc.imoochomework_android02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton01;
    private RadioButton radioButton02;
    private RadioButton radioButton03;
    private ImageView imageView;
    private TextView textView;
    RadioButtonListener radioButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化UI
        initView();
    }

    /**
     * 初始化UI
     */
    private void initView() {
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioButton01 = (RadioButton) findViewById(R.id.rb_01);
        radioButton02 = (RadioButton) findViewById(R.id.rb_02);
        radioButton03 = (RadioButton) findViewById(R.id.rb_03);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    radioGroup.clearCheck();
                    radioGroup.setVisibility(View.GONE);
                    imageView.setImageResource(0);
                    imageView.setVisibility(View.GONE);
                    textView.setText(null);
                    textView.setVisibility(View.GONE);
                } else {
                    radioGroup.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        radioButtonListener = new RadioButtonListener();
        radioGroup.setOnCheckedChangeListener(radioButtonListener);
    }

    //RadioGroup监听实现类
    class RadioButtonListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_01:
                    imageView.setImageResource(R.drawable.office);
                    showCurrentTime();
                    break;
                case R.id.rb_02:
                    imageView.setImageResource(R.drawable.meeting);
                    showCurrentTime();
                    break;
                case R.id.rb_03:
                    imageView.setImageResource(R.drawable.visitor);
                    showCurrentTime();
                    break;
            }
        }
    }

    //显示当前时间
    public void showCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        textView.setText("当前时间：" + sdf.format(new Date()));
    }
}
