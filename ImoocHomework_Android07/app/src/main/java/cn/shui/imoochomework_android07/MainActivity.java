package cn.shui.imoochomework_android07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.List;

import cn.shui.imoochomework_android07.adpater.MenuElvAdapter;
import cn.shui.imoochomework_android07.bean.Menu;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mAddBtn;
    private ExpandableListView mMenuElv;

    private List<Menu> mMenuList;
    private MenuElvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initEvents();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mAddBtn = findViewById(R.id.add_btn);
        mMenuElv = findViewById(R.id.menu_elv);
        mAdapter = new MenuElvAdapter(this, mMenuList);
        mMenuElv.setAdapter(mAdapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化点击事件
     */
    private void initEvents() {
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mMenuElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.e(TAG, "onGroupExpand: " + groupPosition);
            }
        });

        mMenuElv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.e(TAG, "onGroupCollapse: " + groupPosition);
            }
        });

        mMenuElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e(TAG, "onGroupClick: groupPosition=" + groupPosition);
                return false;
            }
        });

        mMenuElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.e(TAG, "onChildClick: groupPosition=" + groupPosition + " childPosition=" + childPosition);
                return false;
            }
        });
    }
}
