package cn.shui.useloaderdemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Loader
 * Created by shui on 2020-02-10
 */
public class UserLoader extends AsyncTaskLoader<List<UserBean>> {
    public UserLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        // 如果Loader启动了，强制执行LoadInBackground()方法
        if (isStarted()) {
            forceLoad();
        }
    }

    /**
     * 在子线程加载数据
     * @return
     */
    @Nullable
    @Override
    public List<UserBean> loadInBackground() {
        // 模拟从网络返回数据
        List<UserBean> list = new ArrayList<>();
        list.add(new UserBean("zhangsan", "123"));
        list.add(new UserBean("lisi", "1234"));
        list.add(new UserBean("wangwu", "12345"));
        return list;
    }
}
