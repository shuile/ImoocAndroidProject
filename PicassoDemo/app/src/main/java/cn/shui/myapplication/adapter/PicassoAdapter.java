package cn.shui.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

public class PicassoAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDataUrisList;

    public PicassoAdapter(Context mContext, List<String> mDataUrisList) {
        this.mContext = mContext;
        this.mDataUrisList = mDataUrisList;
    }

    @Override
    public int getCount() {
        return mDataUrisList != null ? mDataUrisList.size() : 0;
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
        ImageView icon = new ImageView(mContext);

        // 使用下载：自动缓存到磁盘内存或者内存缓存
        Picasso picasso = Picasso.get();
        RequestCreator request = picasso.load(mDataUrisList.get(position));
        request.into(icon);
        return icon;
    }
}
