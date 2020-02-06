package cn.shui.sqlpracticeademo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    private List<Message> msgList;
    private Context mContext;

    public MessageAdapter(List<Message> msgList, Context context) {
        this.msgList = msgList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageHolder msgHolder = new MessageHolder();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (layoutInflater != null) {
                convertView = layoutInflater.inflate(R.layout.item_list_view, null);
            }
            if (convertView != null) {
                msgHolder.mLearnerTv = convertView.findViewById(R.id.learner_tv);
                msgHolder.mNameTv = convertView.findViewById(R.id.name_tv);
                convertView.setTag(msgHolder);
            }
        } else {
            msgHolder = (MessageHolder) convertView.getTag();
        }
        msgHolder.mLearnerTv.setText("" + msgList.get(position).getLearner());
        msgHolder.mNameTv.setText(msgList.get(position).getName());
        return convertView;
    }

    private class MessageHolder {
        private TextView mLearnerTv;
        private TextView mNameTv;
    }
}
