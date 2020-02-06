package com.shui.sqldemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private EditText nameEdt;
    private EditText ageEdt;
    private EditText idEdt;
    private RadioButton genderBtn;
    private RadioGroup genderGp;
    private String genderStr = "男";
    private SQLiteDatabase db;
    private ListView stuList;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加操作
        //数据库名称
        //吐过只有一个数据库名称，那么这个数据库的位置会是在私有目录中
        //如果带SD卡路径，那么数据库位置则在指定的路径下
        String path = Environment.getExternalStorageDirectory() + File.separator + "stu.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(this, path, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                //创建
                Toast.makeText(MainActivity.this, "数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法，那么我们可以将表的创建工作放在这里面完成
//                        String sql = "create table test_tb (_id integer primary key autoincrement,"
//                                + "name varchar(20),"
//                                + "age integer)";
//                        db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                //升级
                Toast.makeText(MainActivity.this, "数据库升级", Toast.LENGTH_SHORT).show();
            }
        };
        //用于获取数据库对象
        //1.数据库存在，则直接打开数据库
        //2.数据库不存在，则调用创建数据库的方法，再打开数据库
        //3.数据库存在，但版本号升高了，则调用数据库升级方法
        db = helper.getReadableDatabase();
//                db.rawQuery();  查询 select * from 表名
//                db.execSQL();   添加、删除、修改、创建表

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

    //SQLiteOpenHelper
    //SQLiteDatabase
    public void operate(View view) {
        String nameStr = nameEdt.getText().toString();
        String ageStr = ageEdt.getText().toString();
        String idStr = idEdt.getText().toString();
        switch (view.getId()) {
            case R.id.insert_btn:
//                String sql = "insert into info_tb (name, age, gender) values('" + nameStr + "', " + ageStr + ", '男')";
                String sql = "insert into info_tb(name, age, gender) values(?, ?, ?)";
                db.execSQL(sql, new String[] {nameStr, ageStr, genderStr});
                mToast.setText("添加11成功");
                mToast.show();
                break;
            case R.id.select_btn:
                //select * from 表名 where _id =
                String sql2 = "select * from info_tb";
                if (!TextUtils.isEmpty(idStr)) {
                    sql2 += " where _id=" + idStr;
                }
                //查询结果
                Cursor cursor = db.rawQuery(sql2, null);
                //SimpleCursorAdapter
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.item,
                        cursor,
                        new String[]{"_id", "name", "age", "gender"},
                        new int[]{R.id.id_item, R.id.name_item, R.id.age_item, R.id.gender_item},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                stuList.setAdapter(adapter);
                break;
            case R.id.delete_btn:
                String sql3 = "delete from info_tb where _id=?";
                db.execSQL(sql3, new String[]{idStr});
                mToast.setText("删除成功");
                mToast.show();
                break;
            case R.id.update_btn:
                String sql4 = "update info_tb set name=?, age=?, gender=? where _id=?";
                db.execSQL(sql4, new String[]{nameStr, ageStr, genderStr, idStr});
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
}
