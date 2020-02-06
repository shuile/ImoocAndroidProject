package cn.imooc.imoochomework_android05.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import cn.imooc.imoochomework_android05.R;
import cn.imooc.imoochomework_android05.fragment.FindFragment;
import cn.imooc.imoochomework_android05.fragment.MainFragment;
import cn.imooc.imoochomework_android05.fragment.MeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected MainFragment mMainFragment = new MainFragment();//首页
    protected FindFragment mFindFragment = new FindFragment();//发现
    protected MeFragment mMeFragment = new MeFragment();//我的

    protected LinearLayout mMneuMain;
    protected LinearLayout mMenuFind;
    protected LinearLayout mMenuMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //获取管理类
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mMainFragment)
                .add(R.id.container, mFindFragment)
                .hide(mFindFragment)
                .add(R.id.container, mMeFragment)
                .hide(mMeFragment)
                //事务    默认：显示首页  其他页面：隐藏
                //提交
                .commit();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mMneuMain = (LinearLayout) findViewById(R.id.menu_main);
        mMenuFind = (LinearLayout) findViewById(R.id.menu_find);
        mMenuMe = (LinearLayout) findViewById(R.id.menu_me);

        mMneuMain.setOnClickListener(this);
        mMenuFind.setOnClickListener(this);
        mMenuMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_main://首页
                //获取管理类
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMainFragment)
                        .hide(mFindFragment)
                        .hide(mMeFragment)
                        //事务    默认：显示首页  其他页面：隐藏
                        //提交
                        .commit();
                break;
            case R.id.menu_find://发现
                //获取管理类
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .show(mFindFragment)
                        .hide(mMeFragment)
                        //事务    默认：显示首页  其他页面：隐藏
                        //提交
                        .commit();
                break;
            case R.id.menu_me://我的
                //获取管理类
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .hide(mFindFragment)
                        .show(mMeFragment)
                        //事务    默认：显示首页  其他页面：隐藏
                        //提交
                        .commit();
                break;
        }
    }
}
