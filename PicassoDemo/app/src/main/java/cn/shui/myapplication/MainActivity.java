package cn.shui.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.shui.myapplication.adapter.PicassoAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String PATH_BASE_URI = "http://www.imooc.com/data/picasso/images/";

    private ListView mPicassoLv;

    private List<String> mDataUrisList;
    private PicassoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDataUrisList = new ArrayList<>();
        mDataUrisList.add(PATH_BASE_URI + "1.jpg");
        mDataUrisList.add(PATH_BASE_URI + "2.jpg");
        mDataUrisList.add(PATH_BASE_URI + "3.jpg");
        mDataUrisList.add(PATH_BASE_URI + "4.jpg");
        mDataUrisList.add(PATH_BASE_URI + "5.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mPicassoLv = findViewById(R.id.picasso_lv);
        // 设置图像调试，是否显示调试的图像
        // 红色：代表的是网络下载的图片
        // 黄色：代表的是从磁盘缓存中加载的图片
        // 绿色：代表的是从内存中加载的图片
        Picasso.get().setIndicatorsEnabled(true);
        mAdapter = new PicassoAdapter(this, mDataUrisList);
        mPicassoLv.setAdapter(mAdapter);
    }
}
