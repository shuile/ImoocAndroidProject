package cn.shui.imooc_res02.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shui.imooc_res02.R;
import cn.shui.imooc_res02.bean.Order;
import cn.shui.imooc_res02.bean.Product;
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.util.T;

public class OrderDetailActivity extends BaseActivity {

    private Order mOrder;

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvDesc;
    private TextView mTvPrice;

    private static final String KEY_ORDER = "key_order";

    public static void launch(Context context, Order order) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(KEY_ORDER, order);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        setUpToolbar();
        setTitle("订单详情");

        Intent intent = getIntent();
        if (intent != null) {
            mOrder = (Order) intent.getSerializableExtra(KEY_ORDER);
        }
        if (mOrder == null) {
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
                .load(Config.baseURL + mOrder.getRestaurant().getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(mIvImage);
        mTvTitle.setText(mOrder.getRestaurant().getName());

        List<Order.ProductVo> ps = mOrder.getPs();
        StringBuilder sb = new StringBuilder();
        for (Order.ProductVo productVo : ps) {
            sb.append(productVo.product.getName())
                    .append("*")
                    .append(productVo.count)
                    .append("\n");
        }
        mTvDesc.setText(sb.toString());

        mTvPrice.setText("共消费：" + mOrder.getPrice() + "元");
    }
}
