package cn.shui.imooc_res02.ui.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.shui.imooc_res02.R;
import cn.shui.imooc_res02.UserInfoHolder;
import cn.shui.imooc_res02.bean.Order;
import cn.shui.imooc_res02.bean.User;
import cn.shui.imooc_res02.biz.OrderBiz;
import cn.shui.imooc_res02.net.CommonCallback;
import cn.shui.imooc_res02.ui.adapter.OrderAdapter;
import cn.shui.imooc_res02.ui.view.CircleTransform;
import cn.shui.imooc_res02.ui.view.refresh.SwipeRefresh;
import cn.shui.imooc_res02.ui.view.refresh.SwipeRefreshLayout;
import cn.shui.imooc_res02.util.T;

public class OrderActivity extends BaseActivity {

    private Button mBtnOrder;
    private TextView mTvUsername;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageView mIvIcon;

    private RecyclerView mRecyclerView;
    private OrderAdapter mAdapter;
    private List<Order> mDatas = new ArrayList<>();

    private OrderBiz mOrderBiz = new OrderBiz();

    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();

        initEvent();

        loadDatas();
    }

    private void initView() {
        mBtnOrder = findViewById(R.id.id_btn_order);
        mTvUsername = findViewById(R.id.id_tv_username);
        mRecyclerView = findViewById(R.id.id_recyclerview);
        mSwipeRefreshLayout = findViewById(R.id.id_swiperefresh);
        mIvIcon = findViewById(R.id.id_iv_icon);

        User user  = UserInfoHolder.getInstance().getUser();
        if (user != null) {
            mTvUsername.setText(user.getUsername());
        } else {
            toLoginActivity();
            finish();
            return;
        }

        mSwipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        mAdapter = new OrderAdapter(mDatas, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        Picasso.get()
                .load(R.drawable.icon)
                .placeholder(R.drawable.pictures_no)
                .transform(new CircleTransform())
                .into(mIvIcon);
    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas();
            }
        });

        mSwipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadMore();
            }
        });

        mBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, ProductListActivity.class);
                startActivityForResult(intent, 1001);
            }
        });
    }

    private void loadDatas() {
        startLoadingProgress();
        mOrderBiz.listByPage(0, new CommonCallback<List<Order>>() {
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                T.showToast(e.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);

                if ("用户未登录".equals(e.getMessage())) {
                    toLoginActivity();
                }
            }

            @Override
            public void onSuccess(List<Order> response) {
                stopLoadingProgress();
                mCurrentPage = 1;
                T.showToast("订单更新成功");
                mDatas.clear();
                mDatas.addAll(response);
                mAdapter.notifyDataSetChanged();
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void loadMore() {
        mOrderBiz.listByPage(mCurrentPage++, new CommonCallback<List<Order>>() {
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                mSwipeRefreshLayout.setPullUpRefreshing(false);
                T.showToast(e.getMessage());
                mCurrentPage--;
            }

            @Override
            public void onSuccess(List<Order> response) {
                stopLoadingProgress();
                mSwipeRefreshLayout.setPullUpRefreshing(false);
                if (response.size() <= 0) {
                    T.showToast("没有订单了~");
                    return;
                }
                T.showToast("订单加载成功~");
                mDatas.addAll(response);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrderBiz.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                loadDatas();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            } catch (Exception e) {
                // ignore
            }
        }
        return true;
    }
}
