package cn.imooc.imoochomework_android03;

import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText etReader;
    private RadioGroup rgSex;
    private EditText etBorrowTime;
    private TextView tvBackTime;
    private CheckBox cbHistory;
    private CheckBox cbSuspense;
    private CheckBox cbLiterary;
    private TextView tvAge;
    private SeekBar sbAge;
    private ImageView ivPic;
    private TextView tvBookName;
    private TextView tvBookCategory;
    private TextView tvFitAge;
    private Button btnFind;
    private TextView tvShow;
    private Button btnNext;

    private Person person;
    private List<Book> lists_book;
    private List<Book> lists_next;
    private boolean isHistory;
    private boolean isSuspense;
    private boolean isLiterary;
    private int fitAge;
    private int count = 0;

    private RadioGroupListener radioGroupListener;
    private CheckBoxListener checkBoxListener;
    private SeekBarListener seekBarListener;
    private ButtonListener buttonListener;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvAge.setText(msg.obj.toString());
                    break;
                case 2:
                    Log.e(TAG, "borrowTime=" + msg.obj.toString());
                    etBorrowTime.setText(msg.obj.toString());
                    break;
                case 3:
                    tvFitAge.setText(msg.obj.toString());
                    break;
            }
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //初始化UI
            initView();
            //初始化数据
            initData();
            //设置监听器
            setListener();
        }

        //初始化UI
        private void initView() {
            etReader = (EditText) findViewById(R.id.et_reader);
            rgSex = (RadioGroup) findViewById(R.id.rg_sex);
            etBorrowTime = (EditText) findViewById(R.id.et_borrowTime);
            tvBackTime = (TextView) findViewById(R.id.tv_backTime);
            cbHistory = (CheckBox) findViewById(R.id.cb_history);
            cbSuspense = (CheckBox) findViewById(R.id.cb_suspense);
            cbLiterary = (CheckBox) findViewById(R.id.cb_literary);
            tvAge = (TextView) findViewById(R.id.tv_age);
            sbAge = (SeekBar) findViewById(R.id.sb_age);
            ivPic = (ImageView) findViewById(R.id.iv_pic);
            tvBookName = (TextView) findViewById(R.id.tv_bookName);
            tvBookCategory = (TextView) findViewById(R.id.tv_category);
            tvFitAge = (TextView) findViewById(R.id.tv_fitAge);
            btnFind = (Button) findViewById(R.id.btn_find);
            tvShow = (TextView) findViewById(R.id.tv_show);
            btnNext = (Button) findViewById(R.id.btn_next);
        }

        //初始化数据
        private void initData() {
            person = new Person();
            lists_next = new ArrayList<Book>();
            lists_book = new ArrayList<Book>();
            lists_book.add(new Book("人生感悟", "文学", 18, R.drawable.aa, false, false, true));
            lists_book.add(new Book("边城", "文学", 20, R.drawable.bb, false, false, true));
            lists_book.add(new Book("Sapir", "计算机", 20, R.drawable.cc, false, false, false));
            lists_book.add(new Book("光辉岁月", "历史", 22, R.drawable.dd, true, false, false));
            lists_book.add(new Book("宋词三百首", "文学", 12, R.drawable.ee, false, false, true));
            lists_book.add(new Book("古代文学纲要", "文学", 25, R.drawable.ff, false, false, true));
            lists_book.add(new Book("无花果", "文学", 30, R.drawable.gg, false, false, true));
            lists_book.add(new Book("古镇记忆", "历史", 28, R.drawable.hh, true, false, false));
        }

        //设置监听器
        private void setListener() {
            radioGroupListener = new RadioGroupListener();
            rgSex.setOnCheckedChangeListener(radioGroupListener);

            checkBoxListener = new CheckBoxListener();
            cbHistory.setOnCheckedChangeListener(checkBoxListener);
            cbSuspense.setOnCheckedChangeListener(checkBoxListener);
            cbLiterary.setOnCheckedChangeListener(checkBoxListener);

            seekBarListener = new SeekBarListener();
            sbAge.setOnSeekBarChangeListener(seekBarListener);

            buttonListener = new ButtonListener();
            btnFind.setOnClickListener(buttonListener);
            btnNext.setOnClickListener(buttonListener);
        }

        //RadioGroup监听器类
        class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_male:
                        person.setSex("男");
                        break;
                    case R.id.rb_female:
                        person.setSex("女");
                        break;
                }
            }
        }

        //CheckBox监听器类
        class CheckBoxListener implements CompoundButton.OnCheckedChangeListener {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox checkBox = (CheckBox) buttonView;
                switch (checkBox.getId()) {
                    case R.id.cb_history:
                        if (cbHistory.isChecked()) {
                            isHistory = true;
                        } else {
                            isHistory = false;
                        }
                        break;
                    case R.id.cb_suspense:
                        if (cbSuspense.isChecked()) {
                            isSuspense = true;
                        } else {
                            isSuspense = false;
                        }
                        break;
                    case R.id.cb_literary:
                        if (cbLiterary.isChecked()) {
                            isLiterary = true;
                        } else {
                            isLiterary = false;
                        }
                        break;
                }
            }
        }

        //SeekBar监听器类
        class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                fitAge = seekBar.getProgress();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = fitAge;
                handler.sendMessage(message);
            }
        }

        class ButtonListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_find:
                        checkData();
                        break;
                    case R.id.btn_next:
                        showData();
                        break;
                }
            }
        }

        //查找数据
        private void checkData() {
            count = 0;
            lists_next.clear();
            person.setName(etReader.getText().toString().trim());
            person.setAge(Integer.valueOf(tvAge.getText().toString().trim()));
            String borrowTimeET = etBorrowTime.getText().toString().trim();
            String backTimeTV = tvBackTime.getHint().toString().trim();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Log.e(TAG, "borrowTimeET=" + borrowTimeET + "  backTimeTV=" + backTimeTV);
                Date borrowTime = sdf1.parse(borrowTimeET);
                Date backTime = sdf1.parse(backTimeTV);
                Log.e(TAG, "borrowTime=" + borrowTime.getTime() + "  backTime=" + backTime.getTime());
                if (borrowTime.getTime() > backTime.getTime()) {
                    Toast.makeText(MainActivity.this, "借书的年份晚于还书年份", Toast.LENGTH_SHORT)
                            .show();
                    finish();
                    return;
                }
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                person.setBorrowTime(sdf2.format(borrowTime));
                Message message = Message.obtain();
                message.what = 2;
                message.obj = sdf2.format(borrowTime);
                handler.sendMessage(message);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Book book;
            for (int i = 0; i < lists_book.size(); i++) {
                book = lists_book.get(i);
                if ((book.getFitAge() <= fitAge) && (book.isHistory() == isHistory) && (book.isSuspense() == isSuspense) && (book.isLiterary() == isLiterary)) {
                    lists_next.add(book);
                }
            }

            String s1 = "123";
            s1.concat("abs");
            Message msg = Message.obtain();
            if (lists_next.size() > 0) {
                book = lists_next.get(count);
                ivPic.setImageResource(book.getBookPic());
                tvBookName.setText(book.getBookName());
                tvBookCategory.setText(book.getBookCategory());
                msg.what = 3;
                msg.obj = book.getFitAge();
                handler.sendMessage(msg);
                Toast.makeText(MainActivity.this, "显示：" + person, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(MainActivity.this, "暂无该种类书本", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        private void showData() {
            count++;
            Message message = Message.obtain();
            if (count < lists_next.size()) {
                Book book = lists_next.get(count);
                ivPic.setImageResource(book.getBookPic());
                tvBookName.setText(book.getBookName());
                tvBookCategory.setText(book.getBookCategory());
                message.what = 3;
                message.obj = book.getFitAge();
                handler.sendMessage(message);
                Toast.makeText(MainActivity.this, "显示：" + person, Toast.LENGTH_SHORT)
                        .show();
            } else {
                lists_next.clear();
                Toast.makeText(MainActivity.this, "已经是最后一本了", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
