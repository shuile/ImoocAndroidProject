package cn.shui.imooc_res02.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.shui.imooc_res02.R;
import cn.shui.imooc_res02.bean.Product;
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.util.T;

public class ProductDetailActivity extends BaseActivity {

    private Product mProduct;

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvDesc;
    private TextView mTvPrice;

    private static final String KEY_PRODUCT = "key_product";

    public static void launch(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(KEY_PRODUCT, product);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        setUpToolbar();
        setTitle("详情");

        Intent intent = getIntent();
        if (intent != null) {
            mProduct = (Product) intent.getSerializableExtra(KEY_PRODUCT);
        }
        if (mProduct == null) {
            T.showToast("参数传递错误！");
            return;
        }

        initVew();
    }

    private void initVew() {
        mIvImage = findViewById(R.id.id_iv_image);
        mTvTitle = findViewById(R.id.id_tv_title);
        mTvDesc = findViewById(R.id.id_tv_desc);
        mTvPrice = findViewById(R.id.id_tv_price);

        Picasso.get()
                .load(Config.baseURL + mProduct.getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(mIvImage);
        mTvTitle.setText(mProduct.getName());
        mTvDesc.setText(mProduct.getDescription());
        mTvPrice.setText(mProduct.getPrice() + "");
    }
}
