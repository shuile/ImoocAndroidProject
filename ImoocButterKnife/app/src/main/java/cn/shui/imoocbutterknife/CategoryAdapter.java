package cn.shui.imoocbutterknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shui on 2020-02-01
 */
public class CategoryAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;

    public CategoryAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, -1, objects);

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(getItem(position));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
