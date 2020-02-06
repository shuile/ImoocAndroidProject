package com.caxcot.imoocgridview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caxcot.imoocgridview.R;
import com.caxcot.imoocgridview.model.AppInfo;
import com.caxcot.imoocgridview.model.ImageInfo;

import java.util.List;

public class GridAdapter3 extends BaseAdapter {

    private Context context;
    private List<ImageInfo> imageInfoList;

    public GridAdapter3(Context context, List<ImageInfo> imageInfoList) {
        this.context = context;
        this.imageInfoList = imageInfoList;
    }

    @Override
    public int getCount() {
        return imageInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageInfoList.get(position);
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
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_appIcon);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_appName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageInfo imageInfo = imageInfoList.get(position);
        holder.textView.setText(imageInfo.getText());
        Glide.with(context).load(imageInfo.getImagePath()).placeholder(R.mipmap.ic_launcher).centerCrop().into(holder.imageView);
//        if (imageInfo.getBitmap() == null) {
//            holder.imageView.setImageResource(R.mipmap.ic_launcher);
//        } else {
//            holder.imageView.setImageBitmap(imageInfo.getBitmap());
//        }
        return convertView;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
