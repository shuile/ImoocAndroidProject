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
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.ui.activity.ProductDetailActivity;
import cn.shui.imooc_res02.util.T;
import cn.shui.imooc_res02.vo.ProductItem;

/**
 * Created by shui on 2019-12-15
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListItemViewHolder> {

    private Context mContext;
    private List<ProductItem> mProductItems;
    private LayoutInflater mInflater;

    public ProductListAdapter(Context mContext, List<ProductItem> mProductItems) {
        this.mContext = mContext;
        this.mProductItems = mProductItems;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ProductListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_product_list, parent, false);
        return new ProductListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListItemViewHolder holder, int position) {
        ProductItem productItem = mProductItems.get(position);
        Picasso.get()
                .load(Config.baseURL + productItem.getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(holder.mIvImage);

        holder.mTvName.setText(productItem.getName());
        holder.mTvCount.setText(productItem.count + "");
        holder.mTvLabel.setText(productItem.getLabel());
        holder.mTvPrice.setText(productItem.getPrice() + "元/份");
    }

    @Override
    public int getItemCount() {
        return mProductItems.size();
    }

    public interface OnProductListener {
        void onProductAdd(ProductItem productItem);
        void onProductSub(ProductItem productItem);
    }

    private OnProductListener mOnProductListener;

    public void setmOnProductListener(OnProductListener mOnProductListener) {
        this.mOnProductListener = mOnProductListener;
    }

    class ProductListItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIvImage;
        public TextView mTvName;
        public TextView mTvLabel;
        public TextView mTvPrice;
        public ImageView mIvAdd;
        private ImageView mIvSub;
        private TextView mTvCount;

        public ProductListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mIvImage = itemView.findViewById(R.id.id_iv_image);
            mTvName = itemView.findViewById(R.id.id_tv_name);
            mTvLabel = itemView.findViewById(R.id.id_tv_label);
            mTvPrice = itemView.findViewById(R.id.id_tv_price);
            mIvAdd = itemView.findViewById(R.id.id_iv_add);
            mIvSub = itemView.findViewById(R.id.id_iv_sub);
            mTvCount = itemView.findViewById(R.id.id_tv_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailActivity.launch(mContext, mProductItems.get(getLayoutPosition()));
                }
            });

            mIvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    ProductItem productItem = mProductItems.get(pos);
                    productItem.count += 1;
                    mTvCount.setText(productItem.count + "");
                    // 回调到Activity
                    if (mOnProductListener != null) {
                        mOnProductListener.onProductAdd(productItem);
                    }
                }
            });

            mIvSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    ProductItem productItem = mProductItems.get(pos);

                    if (productItem.count <= 0) {
                        T.showToast("已经是0了，你想干嘛~");
                        return;
                    }

                    productItem.count -= 1;
                    mTvCount.setText(productItem.count + "");
                    // 回调到Activity
                    if (mOnProductListener != null) {
                        mOnProductListener.onProductSub(productItem);
                    }
                }
            });
        }
    }
}
