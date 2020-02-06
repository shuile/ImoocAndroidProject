package cn.shui.ormlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cn.shui.ormlitedemo.bean.Student;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private DatabaseHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    private static DatabaseHelper sHelper = null;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new DatabaseHelper(context);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Student.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
