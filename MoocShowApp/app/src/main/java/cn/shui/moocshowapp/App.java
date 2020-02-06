package cn.shui.moocshowapp;

import android.graphics.drawable.Drawable;

/**
 * Created by shui on 2019-12-26
 */
public class App {
    private Drawable appImg;
    private String appName;

    public App() {
    }

    public App(Drawable appImg, String appName) {
        this.appImg = appImg;
        this.appName = appName;
    }

    public Drawable getAppImg() {
        return appImg;
    }

    public void setAppImg(Drawable appImg) {
        this.appImg = appImg;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "App{" +
                "appImg=" + appImg +
                ", appName='" + appName + '\'' +
                '}';
    }
}
