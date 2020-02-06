package cn.shui.sqlpracticeademo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "book.db";
    static final String TABLE_NAME = "book";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + " (_id integer primary key autoincrement," +
            "learner integer," +
            "name text)";

    DatabaseOpenHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private DatabaseOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
