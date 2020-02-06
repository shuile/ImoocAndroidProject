package cn.shui.imooc_res02.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shui.imooc_res02.R;
import cn.shui.imooc_res02.bean.Order;
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.ui.activity.OrderDetailActivity;

/**
 * Created by shui on 2019-12-13
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderItemViewHolder> {

    private List<Order> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public OrderAdapter(List<Order> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_order_list, parent, false);
        return new OrderItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        Order order = mDatas.get(position);
        Picasso.get()
                .load(Config.baseURL + order.getRestaurant().getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(holder.mIvImage);

        if (order.getPs().size() > 0) {
            holder.mTvLabel.setText(order.getPs().get(0).product.getName() + "等" + order.getCount() + "件");
        } else {
            holder.mTvLabel.setText("无消费");
        }

        holder.mTvName.setText(order.getRestaurant().getName());
        holder.mTvPrice.setText("共消费：" + order.getPrice() + "元");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIvImage;
        public TextView mTvName;
        public TextView mTvLabel;
        public TextView mTvPrice;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderDetailActivity.launch(mContext, mDatas.get(getLayoutPosition()));
                }
            });

            mIvImage = itemView.findViewById(R.id.id_iv_image);
            mTvName = itemView.findViewById(R.id.id_tv_name);
            mTvLabel = itemView.findViewById(R.id.id_tv_label);
            mTvPrice = itemView.findViewById(R.id.id_tv_price);
        }
    }
}
