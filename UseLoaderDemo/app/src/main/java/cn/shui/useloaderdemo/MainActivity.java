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
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
    private EditText mNameEt;
    private String filterName;

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

        mNameEt = findViewById(R.id.et_contacts_name);
        // 联系人筛选文本框内容改变后，重新加载Loader的数据
        mNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterName = s.toString();
                // 重新创建Loader
                loaderManager.restartLoader(0, null, MainActivity.this);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        if (!TextUtils.isEmpty(filterName)) {
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filterName));
        }
        CursorLoader cursorLoader = new CursorLoader(this, uri, null,
                null, null, null);
        return cursorLoader;
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
