package cn.imooc.ui_basetestdemo_food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/*
 *步骤
 * 1、初始化控件
 * 2、初始化数据
 * 3、为控件添加监听器
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText name;
    private RadioGroup sex;
    private CheckBox hot, seafood, sour;
    private SeekBar seekBar;
    private Button btn;
    private ImageView imageView;
    private ToggleButton toggleButton;

    private List<Food> lists_food;
    private List<Food> lists_get;
    private Person person;

    private RadioGroupListener radioGroupListener;
    private CheckBoxListener checkBoxListener;
    private SeekBarListener seekBarListener;
    private ButtonListener buttonListener;

    private boolean isSeafood;
    private boolean isHot;
    private boolean isSour;

    private int price;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //为控件添加监听器
        setListener();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.et_name);
        sex = (RadioGroup) findViewById(R.id.rg_sex);
        hot = (CheckBox) findViewById(R.id.cb_hot);
        seafood = (CheckBox) findViewById(R.id.cb_seafood);
        sour = (CheckBox) findViewById(R.id.cb_sour);
        seekBar = (SeekBar) findViewById(R.id.sb_price);
        seekBar.setProgress(30);
        btn = (Button) findViewById(R.id.btn_find);
        imageView = (ImageView) findViewById(R.id.iv_pic);
        toggleButton = (ToggleButton) findViewById(R.id.tb_click);
    }

    private void initData() {
        person = new Person();
        lists_get = new ArrayList<Food>();
        lists_food = new ArrayList<Food>();
        lists_food.add(new Food("麻辣香锅", 55, R.drawable.malaxiangguo, true,false, false));

        lists_food.add(new Food("水煮鱼", 48, R.drawable.shuizhuyu, true, true,false));
        lists_food.add(new Food("麻辣火锅", 80, R.drawable.malahuoguo, true, true,false));

        lists_food.add(new Food("清蒸鲈鱼", 68, R.drawable.qingzhengluyu, false,true, false));

        lists_food.add(new Food("桂林米粉", 15, R.drawable.guilin, false, false,false));
        lists_food.add(new Food("上汤娃娃菜", 28, R.drawable.wawacai, false, false,false));
        lists_food.add(new Food("红烧肉", 60, R.drawable.hongshaorou, false,false, false));
        lists_food.add(new Food("木须肉", 40, R.drawable.muxurou, false, false,false));
        lists_food.add(new Food("酸菜牛肉面", 35, R.drawable.suncainiuroumian,false, false, true));
        lists_food.add(new Food("西芹炒百合", 38, R.drawable.xiqin, false, false,false));

        lists_food.add(new Food("酸辣汤", 40, R.drawable.suanlatang, true, false,
                true));
    }

    private void setListener() {
        radioGroupListener = new RadioGroupListener();
        sex.setOnCheckedChangeListener(radioGroupListener);

        checkBoxListener = new CheckBoxListener();
        hot.setOnCheckedChangeListener(checkBoxListener);
        seafood.setOnCheckedChangeListener(checkBoxListener);
        sour.setOnCheckedChangeListener(checkBoxListener);

        seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);

        buttonListener = new ButtonListener();
        btn.setOnClickListener(buttonListener);
        toggleButton.setOnClickListener(buttonListener);
    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //当用户选择当前RadioGroup组的button时触发
            switch (checkedId) {
                case R.id.rb_male:
                    person.setSex("男");
                    break;
                case R.id.rb_female:
                    person.setSex("女");
                    break;
            }
            Toast.makeText(MainActivity.this, "性别：" + person.getSex(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    class CheckBoxListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //当控件状态改变时触发
            CheckBox checkBox = (CheckBox) buttonView;
            switch (checkBox.getId()) {
                case R.id.cb_hot:
                    if (isChecked) {
                        isHot = true;
                    } else {
                        isHot = false;
                    }
                    break;
                case R.id.cb_seafood:
                    if (isChecked) {
                        isSeafood = true;
                    } else {
                        isSeafood = false;
                    }
                    break;
                case R.id.cb_sour:
                    if (isChecked) {
                        isSour = true;
                    } else {
                        isSour = false;
                    }
                    break;
            }
            Toast.makeText(MainActivity.this,
                    "当前喜好：辣：" + isHot + " 海鲜：" + isSeafood + " 酸：" + isSour,
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            price = seekBar.getProgress();
            Toast.makeText(MainActivity.this, "价格：" + price, Toast.LENGTH_LONG)
                    .show();
        }
    }

    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_find:
                    //当用户点击寻找时，需要筛选信息，并把结果显示在imageVIE晚上
                    count = 0;
                    lists_get.clear();
                    checkData();
                    break;
                case R.id.tb_click:
                    if (!toggleButton.isChecked()) {
                        if (count >= lists_get.size()) {
                            count = lists_get.size() - 1;
                        }
                        Food food = lists_get.get(count);
                        person.setName(name.getText().toString().trim());
                        person.setFood(food);
                        Toast.makeText(MainActivity.this, "选中：" + person, Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        count++;
                        if (count < lists_get.size()) {
                            imageView.setImageResource(lists_get.get(count).getPic());
                        } else {
                            Toast.makeText(MainActivity.this, "已经是最后一张了", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                    break;
            }
        }
    }

    private void checkData() {
        //找出菜品
        for (int i = 0; i < lists_food.size(); i++) {
            Food food = lists_food.get(i);
            if ((food.getPrice() <= price) && (food.isHot() == isHot) && (food.isSeafood() == isSeafood) && food.isSour() == isSour) {
                lists_get.add(food);
            }
        }
        if (lists_get.size() > 0) {
            showPic();
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
            Toast.makeText(MainActivity.this, "未找到相关菜品", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "**********" + lists_get.size());
    }

    private void showPic() {
        if (count < lists_get.size()) {
            imageView.setImageResource(lists_get.get(count).getPic());
        }
    }
}
