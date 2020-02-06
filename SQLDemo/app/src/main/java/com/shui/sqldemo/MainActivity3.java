package com.shui.sqldemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.File;

public class MainActivity3 extends AppCompatActivity {

    private static final String TAG = "MainActivity3";

    private EditText nameEdt;
    private EditText ageEdt;
    private EditText idEdt;
    private RadioButton genderBtn;
    private RadioGroup genderGp;
    private String genderStr = "男";
    private SQLiteDatabase db;
    private ListView stuList;
    private Toast mToast;
    private StudentDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new StudentDao(this);

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        nameEdt = (EditText) findViewById(R.id.name_edt);
        ageEdt = (EditText) findViewById(R.id.age_edt);
        idEdt = (EditText) findViewById(R.id.id_edt);
        genderBtn = findViewById(R.id.male);
        stuList = (ListView) findViewById(R.id.stu_list);

        genderGp = (RadioGroup) findViewById(R.id.gender_gp);
        genderGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    //"男"
                    genderStr = "男";
                } else {
                    //"女"
                    genderStr = "女";
                }
            }
        });
    }

    public void operate(View view) {
        String nameStr = nameEdt.getText().toString();
        String ageStr = ageEdt.getText().toString();
        String idStr = idEdt.getText().toString();
        switch (view.getId()) {
            case R.id.insert_btn:
                Student stu = new Student(nameStr, Integer.parseInt(ageStr), genderStr);
                dao.addStudent(stu);
                mToast.setText("添加成功");
                mToast.show();
                break;
            case R.id.select_btn:
                String key = "";
                String value = "";
                if (!TextUtils.isEmpty(ageStr)) {
                    value = nameStr;
                    key = "name";
                } else if (!TextUtils.isEmpty(ageStr)) {
                    value = ageStr;
                    key = "age";
                } else if (!TextUtils.isEmpty(idStr)) {
                    value = idStr;
                    key = "_id";
                }
                Cursor cursor;
                if (TextUtils.isEmpty(key)) {
                    cursor = dao.getStudent();
                } else {
                    cursor = dao.getStudent(key, value);
                }
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.item,
                        cursor,
                        new String[]{"_id", "name", "age", "gender"},
                        new int[]{R.id.id_item, R.id.name_item, R.id.age_item, R.id.gender_item},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                stuList.setAdapter(adapter);
                break;
            case R.id.delete_btn:
                String[] params = getParams(nameStr, ageStr, idStr);
                Log.e(TAG, "operate: " + params[0] + "  " + params[1]);
                dao.deleteStudent(params[0], params[1]);
                mToast.setText("删除成功");
                mToast.show();
                break;
            case R.id.update_btn:
                Student stu1 = new Student(Integer.parseInt(idStr), nameStr, Integer.parseInt(ageStr), genderStr);
                dao.updateStudent(stu1);
                mToast.setText("修改成功");
                mToast.show();
                break;
            default:
                break;
        }
        clear();
    }

    private void clear() {
        nameEdt.setText("");
        ageEdt.setText("");
        idEdt.setText("");
        genderBtn.setChecked(true);
    }

    public String[] getParams(String nameStr, String ageStr, String idStr) {
        String[] params = new String[2];
        if (!TextUtils.isEmpty(nameStr)) {
            params[1] = nameStr;
            params[0] = "name";
        } else if (!TextUtils.isEmpty(ageStr)) {
            params[1] = ageStr;
            params[0] = "age";
        } else if (!TextUtils.isEmpty(idStr)) {
            params[1] = idStr;
            params[0] = "_id";
        }
        return params;
    }
}
