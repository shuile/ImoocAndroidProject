package cn.shui.imooc_res02.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.shui.imooc_res02.R;
import cn.shui.imooc_res02.bean.Order;
import cn.shui.imooc_res02.bean.Product;
import cn.shui.imooc_res02.biz.OrderBiz;
import cn.shui.imooc_res02.biz.ProductBiz;
import cn.shui.imooc_res02.net.CommonCallback;
import cn.shui.imooc_res02.ui.adapter.ProductListAdapter;
import cn.shui.imooc_res02.ui.view.refresh.SwipeRefresh;
import cn.shui.imooc_res02.ui.view.refresh.SwipeRefreshLayout;
import cn.shui.imooc_res02.util.T;
import cn.shui.imooc_res02.vo.ProductItem;

public class ProductListActivity extends BaseActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTvCount;
    private Button mBtnPay;

    private ProductBiz mProductBiz = new ProductBiz();
    private OrderBiz mOrderBiz = new OrderBiz();

    private RecyclerView mRecyclerView;
    private ProductListAdapter mAdapter;
    private List<ProductItem> mDatas = new ArrayList<>();

    private int mCurrentPage = 0;
    private float mTotalPrice;
    private int mTotalCount;

    private Order mOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        setUpToolbar();
        setTitle("订单");

        initView();
        initEvent();

        loadDatas();
    }

    private void initView() {
        mSwipeRefreshLayout = findViewById(R.id.id_swiperefresh);
        mRecyclerView = findViewById(R.id.id_recyclerview);
        mTvCount = findViewById(R.id.id_tv_count);
        mBtnPay = findViewById(R.id.id_btn_pay);

        mSwipeRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.GREEN, Color.YELLOW);

        mAdapter = new ProductListAdapter(this, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadMore();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas();
            }
        });

        mAdapter.setmOnProductListener(new ProductListAdapter.OnProductListener() {
            @Override
            public void onProductAdd(ProductItem productItem) {
                mTotalCount++;
                mTvCount.setText("数量：" + mTotalCount);
                mTotalPrice += productItem.getPrice();
                mBtnPay.setText(mTotalPrice + "元 立即支付");

                mOrder.addProduct(productItem);
            }

            @Override
            public void onProductSub(ProductItem productItem) {
                mTotalCount--;
                mTvCount.setText("数量：" + mTotalCount);
                mTotalPrice -= productItem.getPrice();

                if (mTotalCount == 0) {
                    mTotalPrice = 0;
                }

                mBtnPay.setText(mTotalPrice + "元 立即支付");

                mOrder.removeProduct(productItem);
            }
        });

        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTotalCount <= 0) {
                    T.showToast("您还没有选择菜品呢~~");
                    return;
                }

                mOrder.setCount(mTotalCount);
                mOrder.setPrice(mTotalPrice);
                mOrder.setRestaurant(mDatas.get(0).getRestaurant());

                startLoadingProgress();

                Log.d("cyt", "onClick:mOrder is " + mOrder.toString());

                mOrderBiz.add(mOrder, new CommonCallback<String>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String response) {
                        Log.d("cyt", "onSuccess: response is " + response);
                        stopLoadingProgress();
                        T.showToast("订单支付成功~");

                        setResult(RESULT_OK);
                        finish();
                    }
                });
            }
        });
    }

    private void loadDatas() {
        startLoadingProgress();
        mProductBiz.listByPage(0, new CommonCallback<List<Product>>() {
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
            public void onSuccess(List<Product> response) {
                stopLoadingProgress();
                mSwipeRefreshLayout.setRefreshing(false);
                mCurrentPage = 1;
                mDatas.clear();
                for (Product p : response) {
                    mDatas.add(new ProductItem(p));
                }
                mAdapter.notifyDataSetChanged();
                // 清空选择的数据，数量价格
                mTotalPrice = 0;
                mTotalCount = 0;

                mTvCount.setText("数量：" + mTotalCount);
                mBtnPay.setText(mTotalPrice + "元 立即支付");
            }
        });
    }

    private void loadMore() {
        startLoadingProgress();
        mProductBiz.listByPage(mCurrentPage++, new CommonCallback<List<Product>>() {
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                T.showToast(e.getMessage());
                mSwipeRefreshLayout.setPullUpRefreshing(false);
                mCurrentPage--;
            }

            @Override
            public void onSuccess(List<Product> response) {
                stopLoadingProgress();
                mSwipeRefreshLayout.setPullUpRefreshing(false);

                if (response.size() <= 0) {
                    T.showToast("没有咯");
                    return;
                }

                T.showToast("又找到" + response.size() + "道菜~");

                for (Product p : response) {
                    mDatas.add(new ProductItem(p));
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProductBiz.onDestroy();
    }
}
