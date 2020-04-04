package cn.shui.recordvoicedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shui on 2020/3/21
 * 适配器：处理聊天记录样式
 */
public class ChattingAdapter extends BaseAdapter {

    private Context mContext;
    private List<Msg> datas;

    public ChattingAdapter(Context context, List<Msg> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = datas.get(position);
//        if (convertView == null) {
            if (msg.getFlag() == 1) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.to_msg, null);
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.from_msg, null);
            }
            TextView mTxt = convertView.findViewById(R.id.msg);
            mTxt.setText(msg.getTxt());
//        }
        return convertView;
    }
}
