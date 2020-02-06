package com.caxcot.imooclistviewdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.app_list_view);

        List<String> appNames = new ArrayList<>();

        appNames.add("QQ");
        appNames.add(" wechat");
        appNames.add("imooc");
        appNames.add("imooc");
        appNames.add("imooc");
        appNames.add("imooc");
        appNames.add("imooc");

        final List<ResolveInfo> appInfos = getAppInfos();

        mListView.setAdapter(new AppListAdapter(appInfos));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String packageName = appInfos.get(position).activityInfo.packageName;

                String className = appInfos.get(position).activityInfo.name;

                ComponentName componentName = new ComponentName(packageName, className);
                Intent intent = new Intent();
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View headerView = layoutInflater.inflate(R.layout.header_list_view, null);
        mListView.addHeaderView(headerView);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    /**
     * 获取所有的用户信息
     *
     * @return
     */
    private List<ResolveInfo> getAppInfos() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return getPackageManager().queryIntentActivities(intent, 0);
    }

    public class AppListAdapter extends BaseAdapter {

        List<ResolveInfo> mAppNameInfos;

        public AppListAdapter(List<ResolveInfo> appNames) {
            this.mAppNameInfos = appNames;
        }

        @Override
        public int getCount() {
            //有多少条数据
            return mAppNameInfos.size();
        }

        @Override
        public Object getItem(int position) {
            //获取当前position位置的这一条
            return mAppNameInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            //返回当前position位置的这一条的ID
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //处理view--data 填充数据的一个过程
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item_app_list_view, null);

                viewHolder.mAppIconImageView = (ImageView) convertView.findViewById(R.id.app_icon_image_view);
                viewHolder.mAppNameTextView = (TextView) convertView.findViewById(R.id.app_name_text_view);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mAppIconImageView = (ImageView) convertView.findViewById(R.id.app_icon_image_view);
            viewHolder.mAppNameTextView = (TextView) convertView.findViewById(R.id.app_name_text_view);

//            appNameTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String packageName = mAppNameInfos.get(position).activityInfo.packageName;
//
//                    String className = mAppNameInfos.get(position).activityInfo.name;
//
//                    ComponentName componentName = new ComponentName(packageName, className);
//                    Intent intent = new Intent();
//                    intent.setComponent(componentName);
//                    startActivity(intent);
//                }
//            });

            return convertView;
        }

        public class ViewHolder {
            public ImageView mAppIconImageView;
            public TextView mAppNameTextView;

        }
    }
}
