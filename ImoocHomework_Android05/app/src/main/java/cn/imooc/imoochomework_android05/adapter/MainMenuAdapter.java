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

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuViewHolder> {

    private Context context;
    private List<Menu> menus;

    public MainMenuAdapter(Context context, List<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @NonNull
    @Override
    public MainMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_menu, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewHolder mainMenuViewHolder, int i) {
        Menu menu = menus.get(i);
        mainMenuViewHolder.mImageViewIcon.setImageResource(menu.getIcon());
        mainMenuViewHolder.mTextViewName.setText(menu.getName());
    }

    @Override
    public int getItemCount() {
        return null != menus ? menus.size() : 0;
    }
}

class MainMenuViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageViewIcon;
    public TextView mTextViewName;

    public MainMenuViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageViewIcon = (ImageView) itemView.findViewById(R.id.img_main_menu);
        mTextViewName = (TextView) itemView.findViewById(R.id.tv_main_menu);
    }
}