package com.caxcot.imoocgridview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caxcot.imoocgridview.R;
import com.caxcot.imoocgridview.model.AppInfo;

import java.util.List;

public class GridAdapter2 extends BaseAdapter {

    private Context context;
    private List<AppInfo> appInfoList;

    public GridAdapter2(Context context, List<AppInfo> appInfoList) {
        this.context = context;
        this.appInfoList = appInfoList;
    }

    @Override
    public int getCount() {
        return appInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_gridview2, null);
            holder = new ViewHolder();
            holder.img_appIcon = (ImageView) convertView.findViewById(R.id.img_appIcon);
            holder.tv_appName = (TextView) convertView.findViewById(R.id.tv_appName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AppInfo appInfo = appInfoList.get(position);
        holder.img_appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.tv_appName.setText(appInfo.getAppName() + " " + appInfo.getVersionName());
        return convertView;
    }

    public class ViewHolder {
        ImageView img_appIcon;
        TextView tv_appName;
    }
}
