package com.caxcot.listviewpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        List<String> stringList = new ArrayList<>();
        for (int i = 1; i < 300; i++) {
            stringList.add("学习天数 " + i);
        }

        listView.setAdapter(new MyAdapter(stringList));
    }

    public class MyAdapter extends BaseAdapter {

        List<String> mStringList;

        public MyAdapter(List<String> mIntegerList) {
            this.mStringList = mIntegerList;
        }

        @Override
        public int getCount() {
            return mStringList.size();
        }

        @Override
        public Object getItem(int position) {
            return mStringList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item_list_view, null);

                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(mStringList.get(position));

            return convertView;
        }

        public class ViewHolder {
            public TextView textView;
        }
    }
}
