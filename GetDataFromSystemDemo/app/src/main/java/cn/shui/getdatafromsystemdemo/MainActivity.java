package cn.shui.getdatafromsystemdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button mSmsBtn;
    private Button mReadContactsBtn;
    private Button mWriteContactsBtn;
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = getContentResolver();

        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mSmsBtn = findViewById(R.id.sms_btn);
        mReadContactsBtn = findViewById(R.id.read_contacts_btn);
        mWriteContactsBtn = findViewById(R.id.write_contacts_btn);

        mSmsBtn.setOnClickListener(this);
        mReadContactsBtn.setOnClickListener(this);
        mWriteContactsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sms_btn:
                // 1.获取内容处理者
                // 2.查询方法
                // sms:short message service
                // content://sms 短信箱
                // content://sms/inbox 收件箱
                // content://sms/sent 发件箱
                // content://sms/draft 草稿箱
                Uri uri = Uri.parse("content://sms");
                Cursor cursor = resolver.query(uri, null, null, null, null);
                // 3.解析Cursor
                // 遍历cursor
                if (cursor == null) {
                    Toast.makeText(this, "短信箱无短信", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!cursor.moveToNext()) {
                    Toast.makeText(this, "短信箱无短信", Toast.LENGTH_SHORT).show();
                }
                while (cursor.moveToNext()) {
                    // 对象，内容
                    // 参数：列索引
                    // c.getString(2)
                    // 遍历该行的列
                    String msg = "";
                    String address = cursor.getString(cursor.getColumnIndex("address"));
                    String body = cursor.getString(cursor.getColumnIndex("body"));
                    msg = address + ":  " + body;
                    Log.e(TAG, "onClick: " + msg);
//                    for (int i = 0; i < cursor.getColumnCount(); i++) {
//                        Log.e(TAG, "onClick: " + cursor.getString(i));
//                    }
                }
                break;
            case R.id.read_contacts_btn:
                // 对于联系人而言，他们的存储方式是将姓名和其他内容（电话号码）由不同点contentProvider操作的
                //首先想象姓名和其他内容属于不同的表
                //而姓名所在的表示主表，其他内容位于从表
                //而主表中的主键会在从表中作为外键使用
                Cursor cursor1 = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (cursor1 == null) {
                    Toast.makeText(this, "无联系人", Toast.LENGTH_SHORT).show();
                    return;
                }
                while (cursor1.moveToNext()) {
//                    ContactsContract.Contacts.DISPLAY_NAME 姓名
//                    ContactsContract.Contacts._ID 主键
                    String name = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String id = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                    Log.e(TAG, "onClick: name=" + name + " id=" + id);

                    String selections = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
                    Cursor cursor2 = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            selections,
                            new String[]{id},
                            null);
                    if (cursor2 == null){
                        continue;
                    }
                    while (cursor2.moveToNext()) {
                        String number = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        name += "   " + number;
                    }
                    Log.e(TAG, "onClick: name=" + name);
//                    String msg = "";
//                    for (int i = 0; i < cursor1.getColumnCount(); i++) {
//                        msg += cursor1.getString(i);
//                    }
//                    Log.e(TAG, "onClick: " + msg);
                }
                cursor1.close();
                break;
            case R.id.write_contacts_btn:
                // 1.往一个ContentProvider中插入一条空数据，获取新生成的id
                // 2.利用刚刚生成的id分别组合姓名和电话号码往另一个ContentProvider中插入数据
                ContentValues values = new ContentValues();
                Uri uri1 = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
                long l = ContentUris.parseId(uri1);

                // 插入姓名
                // 指定姓名列的内容
                values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, "cyt");
                // 指定和姓名关联的编号列的内容
                values.put(ContactsContract.Data.RAW_CONTACT_ID, l);
                // 指定该行数据的类型
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                Uri uri2 = resolver.insert(ContactsContract.Data.CONTENT_URI, values);

                // 插入电话号码
                // 清空ContentValues对象
                values.clear();
                // 指定电话号码列的内容
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "12345678901");
                // 指定和电话号码关联的编号列的内容
                values.put(ContactsContract.Data.RAW_CONTACT_ID, l);
                // 指定该行数据的类型
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                // 指定联系方式的类型
                values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                break;
            default:
                break;
        }
    }
}
