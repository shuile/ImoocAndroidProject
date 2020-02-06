package com.caxcot.imoocgridview;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.caxcot.imoocgridview.adapter.GridAdapter2;
import com.caxcot.imoocgridview.adapter.GridAdapter3;
import com.caxcot.imoocgridview.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class ExampleActivity2 extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);

        gridView = (GridView) findViewById(R.id.gridView);

        GridAdapter2 gridAdapter2 = new GridAdapter2(this, getAppList());

        gridView.setAdapter(gridAdapter2);

    }

    public List<AppInfo> getAppList() {
        List<AppInfo> appInfoList = new ArrayList<>();

        PackageManager packageManager = getPackageManager();

        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);

        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            AppInfo appInfo = new AppInfo();
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
            appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(packageManager));
            appInfo.setPackageName(packageInfo.packageName);
            appInfo.setVersionCode(packageInfo.versionCode);
            appInfo.setVersionName(packageInfo.versionName);

            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                appInfoList.add(appInfo);
            }
        }

        return appInfoList;
    }
}
