package com.shui.sqldemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class StudentDao {
    private SQLiteDatabase db;

    public StudentDao(Context context) {
        String path = Environment.getExternalStorageDirectory() + File.separator + "stu.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, path, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };
        db = helper.getReadableDatabase();
    }

    public void addStudent(Student stu) {
        String sql = "insert into info_tb (name, age, gender) values(?, ?, ?)";
        db.execSQL(sql, new String[] {stu.getName(), stu.getAge() + "", stu.getGender()});
    }

    public Cursor getStudent(String... strs) {
        //1.查询所有
        String sql = "select * from info_tb ";
        //2.含条件查询(姓名/年龄/编号) (参数形式：第一个参数指明条件，第二个参数指明条件值)
        if (strs.length != 0) {
            sql  = sql.concat("where " + strs[0] + "='" + strs[1] + "'");
        }
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public ArrayList<Student> getStudentInList(String... strs) {
        ArrayList<Student> list = new ArrayList<>();
        Cursor cursor = getStudent(strs);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String gender = cursor.getString(3);
            Student s = new Student(id, name, age, gender);
            list.add(s);
        }
        return list;
    }

    public void deleteStudent(String... strs) {
        String sql = "delete from info_tb where " + strs[0] + "='" + strs[1] + "'";
        db.execSQL(sql);
    }

    public void updateStudent(Student stu) {
        String sql = "update info_tb set name=?, age=?, gender=? where _id=?";
        db.execSQL(sql, new Object[]{stu.getName(), stu.getAge(), stu.getGender(), stu.getId()});
    }
}
