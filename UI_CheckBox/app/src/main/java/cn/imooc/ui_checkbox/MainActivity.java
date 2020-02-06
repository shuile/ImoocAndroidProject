package cn.imooc.ui_checkbox;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CheckBox cb_game;
    private CheckBox cb_travel;
    private CheckBox cb_read;
    private MyCheckBoxListener checkBoxListener;
    private Button btnAll;
    private Button btnNotAll;
    private Button btnGetResult;
    private TextView showresult;
    private List<String> lists;
    private ButtonListener buttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);

        initView();
        initData();
        setListener();
    }

    private void initView() {
        //初始化控件
        cb_game = (CheckBox) findViewById(R.id.cb_game);
        cb_travel = (CheckBox) findViewById(R.id.cb_travel);
        cb_read = (CheckBox) findViewById(R.id.cb_read);
        btnAll = (Button) findViewById(R.id.btn_all);
        btnNotAll = (Button) findViewById(R.id.btn_not_all);
        btnGetResult = (Button) findViewById(R.id.btn_getResult);
        showresult = (TextView) findViewById(R.id.btn_showResult);
    }

    private void initData() {
        //初始化数据
        lists = new ArrayList<String>();
    }

    private void setListener() {
        checkBoxListener = new MyCheckBoxListener();
        cb_game.setOnCheckedChangeListener(checkBoxListener);
        cb_travel.setOnCheckedChangeListener(checkBoxListener);
        cb_read.setOnCheckedChangeListener(checkBoxListener);

        buttonListener = new ButtonListener();
        btnAll.setOnClickListener(buttonListener);
        btnNotAll.setOnClickListener(buttonListener);
        btnGetResult.setOnClickListener(buttonListener);
    }

    class MyCheckBoxListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //当选中状态发生变化时触发
            CheckBox checkBox = (CheckBox) buttonView;
            switch (checkBox.getId()) {
                case R.id.cb_game:
                    Toast.makeText(MainActivity.this, "电竞：" + isChecked, Toast.LENGTH_SHORT)
                            .show();
                    if (cb_game.isChecked()) {
                        Toast.makeText(MainActivity.this, "少玩游戏多写代码", Toast.LENGTH_SHORT)
                                .show();
                        cb_game.setTextColor(Color.RED);
                    } else {
                        cb_game.setTextColor(Color.BLACK);
                    }
                    break;
                case R.id.cb_travel:
                    Toast.makeText(MainActivity.this, "旅游：" + isChecked, Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.cb_read:
                    Toast.makeText(MainActivity.this, "阅读：" + isChecked, Toast.LENGTH_SHORT)
                            .show();
                    break;
                default:
                    break;
            }
        }
    }

    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_all:
                    cb_game.setChecked(true);
                    cb_travel.setChecked(true);
                    cb_read.setChecked(true);
                    break;
                case R.id.btn_not_all:
                    cb_game.setChecked(false);
                    cb_travel.setChecked(false);
                    cb_read.setChecked(false);
                    break;
                case R.id.btn_getResult:
                    if (cb_game.isChecked()) {
                        lists.add(cb_game.getText().toString().trim());
                    }
                    if (cb_travel.isChecked()) {
                        lists.add(cb_travel.getText().toString().trim());
                    }
                    if (cb_read.isChecked()) {
                        lists.add(cb_read.getText().toString().trim());
                    }
                    showresult.setText(lists.toString());
                    lists.clear();
                    break;
                default:
                    break;
            }
        }
    }
}
