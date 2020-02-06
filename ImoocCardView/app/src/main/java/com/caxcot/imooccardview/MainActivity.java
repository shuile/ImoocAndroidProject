package com.caxcot.imooccardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLvMsgList;
    private List<Msg> mDatas = new ArrayList<>();
    private MsgAdapter mAdapyter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLvMsgList = (ListView) findViewById(R.id.id_lv_msgList);

        mDatas.addAll(MsgLab.generateMockList());
        mDatas.addAll(MsgLab.generateMockList());

        mAdapyter = new MsgAdapter(this, mDatas);
        mLvMsgList.setAdapter(mAdapyter);
    }
}
