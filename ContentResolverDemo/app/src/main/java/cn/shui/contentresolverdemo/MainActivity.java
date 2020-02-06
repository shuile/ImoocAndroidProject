package cn.shui.contentresolverdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.shui.contentresolverdemo.adapter.DataAdapter;
import cn.shui.contentresolverdemo.bean.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private EditText mNameEt;
    private EditText mAgeEt;
    private RadioGroup mSexRg;
    private RadioButton mMaleRb;
    private RadioButton mFemaleRb;
    private EditText mNumberEt;
    private Button mAddBtn;
    private Button mQueryBtn;
    private Button mDeleteBtn;
    private Button mUpdateBtn;
    private ListView mDataLv;

    private DataAdapter mAdapter;
    private List<Data> mDataList;

    private ContentResolver mResolver;
    private String gender;
    private Button mParseUriMatcherBtn;
    private Button mParseUriBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // 获取ContentResolver对象
        mResolver = getContentResolver();
//        mResolver.query()
//        mResolver.insert()
//        mResolver.delete()
//        mResolver.update()
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mNameEt = findViewById(R.id.name_et);
        mAgeEt = findViewById(R.id.age_et);
        mSexRg = findViewById(R.id.sex_rg);
        mMaleRb = findViewById(R.id.male_rb);
        mFemaleRb = findViewById(R.id.female_rb);
        mNumberEt = findViewById(R.id.number_et);
        mAddBtn = findViewById(R.id.insert_btn);
        mQueryBtn = findViewById(R.id.query_btn);
        mDeleteBtn = findViewById(R.id.delete_btn);
        mUpdateBtn = findViewById(R.id.update_btn);
        mDataLv = findViewById(R.id.data_lv);
        mParseUriMatcherBtn = findViewById(R.id.parse_uriMatcher_btn);
        mParseUriBtn = findViewById(R.id.parse_uri_btn);

        mAddBtn.setOnClickListener(this);
        mQueryBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        mUpdateBtn.setOnClickListener(this);
        mParseUriMatcherBtn.setOnClickListener(this);
        mParseUriBtn.setOnClickListener(this);

        mDataList = new ArrayList<>();
        mAdapter = new DataAdapter(this, mDataList);
        mDataLv.setAdapter(mAdapter);

        mSexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male_rb) {
                    gender = "男";
                } else {
                    gender = "女";
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Uri uri1 = Uri.parse("content://cn.shui.myprovider");

        String name = mNameEt.getText().toString();
        String age = mAgeEt.getText().toString();
        String _id = mNumberEt.getText().toString();
        switch (v.getId()) {
            case R.id.insert_btn:
                // 参数1：URI（Uniform Resource Identife    r，统一资源定位符）对象，content://authorities[/path]
                // 参数2：
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                values.put("gender", gender);
                Uri uri2 = mResolver.insert(uri1, values);
                long id = ContentUris.parseId(uri2);
                Toast.makeText(this, "添加成功，新学生的学号是：" + id, Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_btn:
                // 查询所有
                // 参数2：查询列，为null代表查询所有
                Cursor cursor = mResolver.query(uri1, null, null, null, null);
                if (cursor == null) {
                    return;
                }
                // 参数2：每一个学员信息对象所显示的样式布局
                // 参数3：数据源
                // 参数4：查询结果中所有列的列名
                // 参数5：数据未来所要加载到的对应控件的id数目
                // 参数6：
//                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                        R.layout.data_item_list_view,
//                        cursor,
//                        new String[]{"_id", "name","age","gender"},
//                        new int[]{R.id.number_tv, R.id.name_tv, R.id.age_tv, R.id.sex_tv},
//                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//                mDataLv.setAdapter(adapter);
                mDataList.clear();
                while (cursor.moveToNext()) {
                    int id1 = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name1 = cursor.getString(cursor.getColumnIndex("name"));
                    String age1 = cursor.getString(cursor.getColumnIndex("age"));
                    String gender = cursor.getString(cursor.getColumnIndex("gender"));
                    Data data = new Data("" + id1, name1, age1, gender);
                    mDataList.add(data);
                }
                mAdapter.notifyDataSetChanged();
                cursor.close();
                break;
            case R.id.delete_btn:
                int delete = mResolver.delete(uri1, "_id=?", new String[]{_id});
                if (delete > 0) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update_btn:
                ContentValues values1 = new ContentValues();
                values1.put("name", name);
                values1.put("age", age);
                values1.put("gender", gender);
                int update = mResolver.update(uri1, values1, "_id=?", new String[]{_id});
                if (update > 0) {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.parse_uriMatcher_btn:
                mResolver.delete(Uri.parse("content://cn.shui.myprovider/helloworld"), null, null);
                mResolver.delete(Uri.parse("content://cn.shui.myprovider/helloworld/abc"), null, null);
                mResolver.delete(Uri.parse("content://cn.shui.myprovider/helloworld/123"), null, null);
                mResolver.delete(Uri.parse("content://cn.shui.myprovider/helloworld/0909"), null, null);
                mResolver.delete(Uri.parse("content://cn.shui.myprovider/helloworld/89ii"), null, null);
                mResolver.delete(Uri.parse("content://cn.shui.myprovider/nihaoshijie/89ii"), null, null);
                break;
            case R.id.parse_uri_btn:
                Uri uri = mResolver.insert(Uri.parse("content://cn.shui.myprovider/whatever?name=张三&age=23&gender=男"),
                        new ContentValues());
                long l = ContentUris.parseId(uri);
                Toast.makeText(this, "添加成功，也意味着uri解析成功，新学员学号是：" + l, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
