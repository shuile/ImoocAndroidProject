package cn.imooc.imoochomework_android05.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.imooc.imoochomework_android05.R;
import cn.imooc.imoochomework_android05.entity.Menu;

public class SecondMenuAdapter extends RecyclerView.Adapter<SecondMenuViewHolder> {

    private Context context;
    private List<Menu> menus;

    public SecondMenuAdapter(Context context, List<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @NonNull
    @Override
    public SecondMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SecondMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_second_menu, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SecondMenuViewHolder secondMenuViewHolder, int i) {
        Menu menu = menus.get(i);
        secondMenuViewHolder.mImageViewIcon.setImageResource(menu.getIcon());
        secondMenuViewHolder.mTextViewName.setText(menu.getName());
    }

    @Override
    public int getItemCount() {
        return null != menus ? menus.size() : 0;
    }
}

class SecondMenuViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageViewIcon;
    public TextView mTextViewName;

    public SecondMenuViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageViewIcon = (ImageView) itemView.findViewById(R.id.img_second_menu);
        mTextViewName = (TextView) itemView.findViewById(R.id.tv_second_menu);
    }
}
