package cn.imooc.imoochomework_android04;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                showDateDialog();
                break;
            case R.id.button2:
                showSingleDialog();
                break;
            case R.id.button3:
                showMultiDialog();
                break;
        }
    }

    private void showMultiDialog() {
        final String[] items = {"C++", "C", "Java", "Python", "Go", "Ruby", "Object-C"};
        final boolean[] checked = {false, false, false, false, false, false, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("选择个人爱好")
                .setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "个人爱好:\n";
                        for (int i = 0; i < checked.length; i++) {
                            if (checked[i]) {
                                str += "  " + items[i] + "\n";
                            }
                        }
                        ((TextView) findViewById(R.id.textView)).setText(str);
                    }
                });
        builder.show();
    }

    private void showSingleDialog() {
        final String[] items = {"男", "女", "性别未知", "你猜"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("性别选择")
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您选择的性别是：" + items[which], Toast.LENGTH_SHORT)
                                .show();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String title = "";
        switch (dayOfWeek) {
            case 1:
                title += "Sun, ";
                break;
            case 2:
                title += "Mon, ";
                break;
            case 3:
                title += "Tue, ";
                break;
            case 4:
                title += "Wed, ";
                break;
            case 5:
                title += "Thu, ";
                break;
            case 6:
                title += "Fri, ";
                break;
            case 7:
                break;
        }
        switch (month) {
            case 0:
                title += "Jan ";
                break;
            case 1:
                title += "Feb ";
                break;
            case 2:
                title += "Mar ";
                break;
            case 3:
                title += "Apr ";
                break;
            case 4:
                title += "May ";
                break;
            case 5:
                title += "Jun ";
                break;
            case 6:
                title += "Jul ";
                break;
            case 7:
                title += "Aug ";
                break;
            case 8:
                title += "Sep ";
                break;
            case 9:
                title += "Oct ";
                break;
            case 10:
                title += "Nov ";
                break;
            case 11:
                title += "Dec ";
                break;
        }
        title += dayOfMonth + ", " + year;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this, year + "年" + month + "月" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        }, year, month, dayOfMonth);
        datePickerDialog.setTitle(title);
        datePickerDialog.show();
    }
}
