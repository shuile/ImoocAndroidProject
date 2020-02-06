package cn.shui.moocshowapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shui on 2019-12-26
 */
public class AppAdapter extends BaseAdapter {

    private Context mContext;
    private List<App> mAppList;

    public AppAdapter(Context mContext, List<App> mAppList) {
        this.mContext = mContext;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppViewHolder holder;
        if (convertView == null) {
            holder = new AppViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_app_info_layout, null, true);
            holder.mAppImg = convertView.findViewById(R.id.iv_app_img);
            holder.mAppName = convertView.findViewById(R.id.tv_app_name);
            convertView.setTag(holder);
        } else {
            holder = (AppViewHolder) convertView.getTag();
        }
        holder.mAppImg.setImageResource(mAppList.get(position).getAppImg());
        holder.mAppName.setText(mAppList.get(position).getAppName());
        return convertView;
    }

    class AppViewHolder {
        private ImageView mAppImg;
        private TextView mAppName;
    }
}
