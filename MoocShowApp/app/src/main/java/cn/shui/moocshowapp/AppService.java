package cn.shui.moocshowapp;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppService extends Service implements MainListener {

    private List<App> mAllAppList = new ArrayList<>();

    public AppService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 获取手机中应用信息
        getAppInfoList();
        return super.onStartCommand(intent, flags, startId);
    }

    public void getAppInfoList() {
        PackageManager packageManager = AppApplication.getInstance().getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        for (ApplicationInfo info : installedApplications) {
            Drawable icon = info.loadIcon(packageManager);
            String label = (String) info.loadLabel(packageManager);
            App app = new App(icon, label);
            mAllAppList.add(app);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void contentChanged(String str) {
        Map<String, Drawable> map = new HashMap<>();
        for (App app : mAllAppList) {
            if (!TextUtils.isEmpty(str) && !app.getAppName().contains(str)) {
                continue;
            }
            map.put(app.getAppName(), app.getAppImg());
        }
        Intent intent = new Intent(MainActivity.BROADCAST);
    }
}
