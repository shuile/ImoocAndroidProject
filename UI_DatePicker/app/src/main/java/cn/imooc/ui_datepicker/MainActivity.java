package cn.imooc.ui_datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    private TextView textView;
    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        getCurrentTime();

        setPicker();
    }

    private void getCurrentTime() {
        //获取系统当前时间
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void setPicker() {
        //设置时间选择器为24小时制
        timePicker.setIs24HourView(true);
        //设置时间选择器的当前时间
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //当时间选择器发生改变时，触发
                System.out.println("From TimePicker:" + hourOfDay + ":" + minute);
                Log.e("cyt", "From TimePicker:" + hourOfDay + ":" + minute);
            }
        });
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //当日期选择器发生改变时，触发
                System.out.println("From DatePicker:" + year + "/" + monthOfYear + "/" + dayOfMonth);
                Log.e("cyt", "From DatePicker:" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        });
    }

    private void initView() {
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        textView = (TextView) findViewById(R.id.tv_content);
    }
}
