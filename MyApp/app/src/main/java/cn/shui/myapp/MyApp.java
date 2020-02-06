package cn.shui.myapp;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import com.squareup.otto.Bus;

/**
 * Created by shui on 2019-12-19
 */
public class MyApp extends Application {

    private static final String TAG = "MyApp-app";

    private Bus bus;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " + this);
        Log.d(TAG, "onCreate: " + Thread.currentThread());

        bus = new Bus();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: called with: " + "newConfig = [" + newConfig + "]");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }
}
