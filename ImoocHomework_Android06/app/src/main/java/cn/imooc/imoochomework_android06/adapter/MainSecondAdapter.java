package cn.imooc.imoochomework_android06.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.imooc.imoochomework_android06.R;
import cn.imooc.imoochomework_android06.entity.Food;

public class MainSecondAdapter extends RecyclerView.Adapter<MainSecondViewHolder> {

    private Context context;
    private List<Food> foodList;

    public MainSecondAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MainSecondViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainSecondViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_second, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MainSecondViewHolder mainSecondViewHolder, int i) {
        Food food = foodList.get(i);
        mainSecondViewHolder.foodPicIv.setImageResource(food.getFoodPic());
        mainSecondViewHolder.foodNameTv.setText(food.getFoodName());
        mainSecondViewHolder.foodDesTv.setText(food.getFoodDes());
        mainSecondViewHolder.foodPriceTv.setText("￥" + food.getFoodPrice());
        if (food.isNewCustomer()) {
            mainSecondViewHolder.isNewCustomerTv.setText("新用户一元抢");
        } else {
            mainSecondViewHolder.isNewCustomerTv.setText("老用户折扣享");
        }
        mainSecondViewHolder.saleNumberTv.setText("已售:" + food.getSaleNumber());
    }

    @Override
    public int getItemCount() {
        return null != foodList ? foodList.size() : 0;
    }
}

class MainSecondViewHolder extends RecyclerView.ViewHolder {

    public ImageView foodPicIv;
    public TextView foodNameTv;
    public TextView foodDesTv;
    public TextView foodPriceTv;
    public TextView isNewCustomerTv;
    public TextView saleNumberTv;

    public MainSecondViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
