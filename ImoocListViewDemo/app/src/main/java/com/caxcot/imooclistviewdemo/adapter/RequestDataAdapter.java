package com.caxcot.imooclistviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caxcot.imooclistviewdemo.R;
import com.caxcot.imooclistviewdemo.model.LessonResult;

import java.util.ArrayList;
import java.util.List;

public class RequestDataAdapter extends BaseAdapter {

    List<LessonResult.LessonInfo> mLessonInfos = new ArrayList<>();
    Context mContext;

    public RequestDataAdapter(Context context, List<LessonResult.LessonInfo> mLessonInfos) {
        this.mLessonInfos = mLessonInfos;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mLessonInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mLessonInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_app_list_view, null);
            viewHolder.mAppIconImageView = convertView.findViewById(R.id.app_icon_image_view);
            viewHolder.mAppNameTextView = convertView.findViewById(R.id.app_name_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mAppNameTextView.setText(mLessonInfos.get(position).getmName());
        viewHolder.mAppIconImageView.setVisibility(View.GONE);

        return convertView;
    }

    public class ViewHolder {

        public ImageView mAppIconImageView;
        public TextView mAppNameTextView;
    }
}
