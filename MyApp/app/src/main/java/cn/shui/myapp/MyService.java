package cn.shui.myapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * Created by shui on 2019-12-19
 */
public class MyService extends IntentService {

    private static final String TAG = "MyService-app";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyService() {
        super("MyService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: " + getApplication() + ", " + this);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
