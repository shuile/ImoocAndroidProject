package com.shui.sqldemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity2 extends AppCompatActivity {

    private EditText nameEdt;
    private EditText ageEdt;
    private EditText idEdt;
    private RadioButton genderBtn;
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
                Toast.makeText(MainActivity2.this, "数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法，那么我们可以将表的创建工作放在这里面完成
//                        String sql = "create table test_tb (_id integer primary key autoincrement,"
//                                + "name varchar(20),"
//                                + "age integer)";
//                        db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                //升级
                Toast.makeText(MainActivity2.this, "数据库升级", Toast.LENGTH_SHORT).show();
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

        nameEdt = findViewById(R.id.name_edt);
        ageEdt = findViewById(R.id.age_edt);
        idEdt = findViewById(R.id.id_edt);
        genderBtn = findViewById(R.id.male);
        stuList = findViewById(R.id.stu_list);

        RadioGroup genderGp = findViewById(R.id.gender_gp);
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
                //在SqlliteDatabase类下，提供四个方法
                //insert（添加）、delete（删除）、update（修改）、query（查询）
                //都不需要写sql语句
                //参数1：你所要操作的数据库表的名称
                //参数2：可以为空的列.如果第三个参数是null或者说里面没有数据
                //那么我们的sql语句就会变为insert into info_tb () values(),在语法上就是错误的
                //此时通过参数3指定一个可以为空的列，语句就变成了insert into info_tb (可空列) values (null)
                ContentValues values = new ContentValues();
                //insert into 表明（列1， 列2） values（值1， 值2）
                values.put("name", nameStr);
                values.put("age", ageStr);
                values.put("gender", genderStr);
                long id = db.insert("info_tb", null, values);
                mToast.setText("添加成功，新学员学号是：" + id);
                mToast.show();
                break;
            case R.id.select_btn:
                //select 列名 from 表名 where 列1 = 值1
                //参数2：你所要查询的列。{"name", "age", "gender"},查询所有传入null/{"*"}
                //参数3：条件(针对列)
                //参数5：分组
                //参数6：当group by对数据进行分组后，可以通过having来去除不符合条件的组
                Cursor cursor = db.query("info_tb", null, null, null, null, null, null);

                //SimpleCursorAdapter
                //SimpleCursorAdapter a = new SimpleCursorAdapter()
                //参数3：数据源
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.item,
                        cursor,
                        new String[]{"_id", "name", "age", "gender"},
                        new int[]{R.id.id_item, R.id.name_item, R.id.age_item, R.id.gender_item},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                stuList.setAdapter(adapter);
                break;
            case R.id.delete_btn:
                int count = db.delete("info_tb", "_id=?", new String[]{idStr});
                if (count > 0) {
                    mToast.setText("删除成功");
                    mToast.show();
                }
                break;
            case R.id.update_btn:
                ContentValues values1 = new ContentValues();
                //update info_tb set 列1=xx，列2=xxx where 列名=值
                values1.put("name", nameStr);
                values1.put("age", ageStr);
                values1.put("gender", genderStr);
                int count2 = db.update("info_tb", values1, "_id=?", new String[]{idStr});
                if (count2 > 0) {
                    mToast.setText("修改成功");
                    mToast.show();
                }
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
