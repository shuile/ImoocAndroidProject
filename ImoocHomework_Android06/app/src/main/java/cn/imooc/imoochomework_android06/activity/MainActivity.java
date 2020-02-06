package cn.imooc.imoochomework_android06.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import cn.imooc.imoochomework_android06.R;
import cn.imooc.imoochomework_android06.fragment.MainFragment;
import cn.imooc.imoochomework_android06.fragment.ShopFragment;
import cn.imooc.imoochomework_android06.fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected MainFragment mainFragment = new MainFragment();
    protected ShopFragment shopFragment = new ShopFragment();
    protected UserFragment userFragment = new UserFragment();

    private LinearLayout mMainIndexLl;
    private LinearLayout mShopIndexLl;
    private LinearLayout mUserIndexLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initView();
    }

    private void initView() {
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mainFragment)
                .add(R.id.container, shopFragment)
                .hide(shopFragment)
                .add(R.id.container, userFragment)
                .hide(userFragment)
                .commit();

        mMainIndexLl = (LinearLayout) findViewById(R.id.layout_main_index_ll);
        mShopIndexLl = (LinearLayout) findViewById(R.id.layout_shop_index_ll);
        mUserIndexLl = (LinearLayout) findViewById(R.id.layout_user_index_ll);

        mMainIndexLl.setOnClickListener(this);
        mShopIndexLl.setOnClickListener(this);
        mUserIndexLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_main_index_ll:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mainFragment)
                        .hide(shopFragment)
                        .hide(userFragment)
                        .commit();
                break;
            case R.id.layout_shop_index_ll:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mainFragment)
                        .show(shopFragment)
                        .hide(userFragment)
                        .commit();
                break;
            case R.id.layout_user_index_ll:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mainFragment)
                        .hide(shopFragment)
                        .show(userFragment)
                        .commit();
                break;
        }
    }
}
