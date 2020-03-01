package cn.shui.useloaderdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<UserBean>> {

    private List<UserBean> users = new ArrayList<>();
    private ListView mListView;
    private UserAdapter adapter;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loader);

        mListView = findViewById(R.id.lv_custom_loader);
        adapter = new UserAdapter();
        mListView.setAdapter(adapter);

        loaderManager = getSupportLoaderManager();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 0);
        } else {
            // 初始化Loader，参数1：Loader的ID，参数2：给Loader传递的参数，参数3：LoaderCallbacks接口
            loaderManager.initLoader(0, null, this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 初始化Loader，参数1：Loader的ID，参数2：给Loader传递的参数，参数3：LoaderCallbacks接口
                loaderManager.initLoader(0, null, this);
            } else {
                finish();
            }
        }
    }

    @NonNull
    @Override
    public Loader<List<UserBean>> onCreateLoader(int id, @Nullable Bundle args) {
        // 返回自定义Loader
        return new UserLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<UserBean>> loader, List<UserBean> data) {
        users.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<UserBean>> loader) {

    }

    class UserAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(CustomLoaderActivity.this).inflate(R.layout.user_item, null);
                holder = new ViewHolder();
                holder.usernameTv = convertView.findViewById(R.id.tv_username);
                holder.passwordTv = convertView.findViewById(R.id.tv_password);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.usernameTv.setText(users.get(position).getUsername());
            holder.passwordTv.setText(users.get(position).getPassword());
            return convertView;
        }

        class ViewHolder {
            TextView usernameTv;
            TextView passwordTv;
        }
    }
}
