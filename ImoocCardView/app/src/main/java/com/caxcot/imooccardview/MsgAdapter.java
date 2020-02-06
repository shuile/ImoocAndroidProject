package com.caxcot.imooccardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Msg> mDatas;

    public MsgAdapter(Context mContext, List<Msg> mDatas) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_msg, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mIvImg = convertView.findViewById(R.id.id_iv_img);
            viewHolder.mTvTitle = convertView.findViewById(R.id.id_tv_title);
            viewHolder.mTvContent = convertView.findViewById(R.id.id_tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Msg msg = mDatas.get(position);
        viewHolder.mIvImg.setImageResource(msg.getImgResId());
        viewHolder.mTvTitle.setText(msg.getTitle());
        viewHolder.mTvContent.setText(msg.getContent());

        return convertView;
    }

    public static class ViewHolder {
        ImageView mIvImg;
        TextView mTvTitle;
        TextView mTvContent;
    }
}
