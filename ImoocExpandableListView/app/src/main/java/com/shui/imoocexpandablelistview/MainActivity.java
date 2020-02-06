package com.shui.imoocexpandablelistview;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;


import com.shui.imoocexpandablelistview.adapter.ChapterAdapter;
import com.shui.imoocexpandablelistview.bean.Chapter;
import com.shui.imoocexpandablelistview.bean.ChapterLab;
import com.shui.imoocexpandablelistview.biz.ChapterBiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ExpandableListView mExpandableListView;
    private BaseExpandableListAdapter mAdapter;
    private List<Chapter> mDatas = new ArrayList<>();
    private ChapterBiz mChapterBiz = new ChapterBiz();

    private Button mbtnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvents();

        loadDatas(true);
    }

    private void loadDatas(boolean useCache) {
        mChapterBiz.loadDatas(this, new ChapterBiz.Callback() {
            @Override
            public void onSuccess(List<Chapter> chapterList) {
                mDatas.clear();
                mDatas.addAll(chapterList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
            }
        }, useCache);
    }

    private void initEvents() {
        mbtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(false);
            }
        });
//        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Log.d(TAG, "onChildClick: groupPosition=" + groupPosition + " childPosition=" + childPosition);
//                return false;
//            }
//        });
//        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Log.d(TAG, "onChildClick: groupPosition=" + groupPosition);
//                return false;
//            }
//        });
//        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Log.d(TAG, "onChildClick: groupPosition=" + groupPosition);
//            }
//        });
//        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Log.d(TAG, "onChildClick: groupPosition=" + groupPosition);
//            }
//        });
    }

    private void initView() {
        mExpandableListView = findViewById(R.id.expandable_list_view);
        mDatas.clear();
//        mDatas.addAll(ChapterLab.generateMockDatas());
        mAdapter = new ChapterAdapter(this, mDatas);
        mExpandableListView.setAdapter(mAdapter);
        mbtnRefresh = findViewById(R.id.btn_refresh);
    }
}
