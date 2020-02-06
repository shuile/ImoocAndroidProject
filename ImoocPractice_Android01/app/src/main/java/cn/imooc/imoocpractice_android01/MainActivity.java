package cn.imooc.imoocpractice_android01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.imooc.imoocpractice_android01.fragment.AllFragment;
import cn.imooc.imoocpractice_android01.fragment.CreateFragment;
import cn.imooc.imoocpractice_android01.fragment.MeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CreateFragment.OnCreateFragmentListener {

    private AllFragment mAllFragment = new AllFragment();
    private CreateFragment mCreateFragment = new CreateFragment();
    private MeFragment mMeFragment = new MeFragment();

    private LinearLayout mAll;
    private LinearLayout mCreate;
    private LinearLayout mMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mAllFragment)
                .add(R.id.container, mCreateFragment)
                .hide(mCreateFragment)
                .add(R.id.container, mMeFragment)
                .hide(mMeFragment)
                .commit();
        mCreateFragment.setOnCreateFragmentListener(this);
    }

    private void initView() {
        mAll = (LinearLayout) findViewById(R.id.all);
        mCreate = (LinearLayout) findViewById(R.id.create);
        mMe = (LinearLayout) findViewById(R.id.me);

        mAll.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        mMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mAllFragment)
                        .hide(mCreateFragment)
                        .hide(mMeFragment)
                        .commit();
                break;
            case R.id.create:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mAllFragment)
                        .show(mCreateFragment)
                        .hide(mMeFragment)
                        .commit();
                break;
            case R.id.me:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mAllFragment)
                        .hide(mCreateFragment)
                        .show(mMeFragment)
                        .commit();
                break;
        }
    }

    @Override
    public void onCreateFragmentClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .show(mAllFragment)
                .hide(mCreateFragment)
                .hide(mMeFragment)
                .commit();
    }
}
