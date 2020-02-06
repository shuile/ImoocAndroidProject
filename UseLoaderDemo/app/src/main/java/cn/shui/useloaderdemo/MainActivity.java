package cn.shui.useloaderdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

/**
 * 1.获得LoaderManager对象
 * 2.使用LoaderManager来初始化Loader
 * 3.实现LoaderCallbacks接口
 * 4.在onCreateLoader方法中返回CursorLoader，在onLoaderFinished方法中获得加载的数据
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";

    private ListView mContactsLv;
    private LoaderManager loaderManager;
    private SimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContactsLv = findViewById(R.id.lv_contacts_list);
        // 设置游标适配器
        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mContactsLv.setAdapter(mCursorAdapter);

        loaderManager = getSupportLoaderManager();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 0);
        } else {
            // 初始化Loader，参数1：Loader的ID，参数2：给Loader传递的参数，参数3：LoaderCallbacks接口
            loaderManager.initLoader(0, null, this);
        }
    }

    /**
     * 创建Loader对象
     * @param id Loader的ID
     * @param args 给Loader传递的参数
     * @return
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
    }

    /**
     * 加载数据完成
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        // 在主线程完成的,更新UI
        Cursor oldCursor = mCursorAdapter.swapCursor(data);
        if (oldCursor != null) {
            oldCursor.close();
        }
    }

    /**
     * 重置Loader
     * @param loader
     */
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        // 释放当前游标
        Cursor oldCursor = mCursorAdapter.swapCursor(null);
        if (oldCursor != null) {
            oldCursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 初始化Loader，参数1：Loader的ID，参数2：给Loader传递的参数，参数3：LoaderCallbacks接口
                loaderManager.initLoader(0, null, this);
            } else {
                finish();
            }
        }
    }
}
