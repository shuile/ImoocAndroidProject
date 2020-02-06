package cn.shui.moocshowapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by shui on 2019-12-26
 */
public class AppApplication extends Application {

    public static Context getInstance() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
