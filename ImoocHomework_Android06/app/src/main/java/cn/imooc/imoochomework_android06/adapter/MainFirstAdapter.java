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
import cn.imooc.imoochomework_android06.entity.Menu;

public class MainFirstAdapter extends RecyclerView.Adapter<MainFirstViewHolder> {

    private Context context;
    private List<Menu> menuList;

    public MainFirstAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MainFirstViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainFirstViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_first, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MainFirstViewHolder viewHolder, int i) {
        Menu menu = menuList.get(i);
        viewHolder.mMainMenuImageView.setImageResource(menu.getPic());
        viewHolder.mMainMenuTextView.setText(menu.getName());
    }

    @Override
    public int getItemCount() {
        return null != menuList ? menuList.size() : 0;
    }
}

class MainFirstViewHolder extends RecyclerView.ViewHolder {

    public ImageView mMainMenuImageView;
    public TextView mMainMenuTextView;

    public MainFirstViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
