package cn.shui.contentresolverdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.shui.contentresolverdemo.R;
import cn.shui.contentresolverdemo.bean.Data;

public class DataAdapter extends BaseAdapter {

    private Context mContext;
    private List<Data> dataList;

    public DataAdapter(Context mContext, List<Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.data_item_list_view, null);
            holder = new DataHolder();
            holder.mNumberTv = convertView.findViewById(R.id.number_tv);
            holder.mNameTv = convertView.findViewById(R.id.name_tv);
            holder.mAgeTv = convertView.findViewById(R.id.age_tv);
            holder.mSexTv = convertView.findViewById(R.id.sex_tv);
            convertView.setTag(holder);
        } else {
            holder = (DataHolder) convertView.getTag();
        }
        holder.mNumberTv.setText(dataList.get(position).getId());
        holder.mNameTv.setText(dataList.get(position).getName());
        holder.mAgeTv.setText(dataList.get(position).getAge());
        holder.mSexTv.setText(dataList.get(position).getSex());
        return convertView;
    }

    private static class DataHolder {
        private TextView mNumberTv;
        private TextView mNameTv;
        private TextView mAgeTv;
        private TextView mSexTv;
    }
}
