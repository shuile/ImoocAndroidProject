package cn.shui.audiodemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * Created by shui on 2020-03-08
 */
public class MyService extends Service {

    private static final String TAG = "MyService";

    private MediaPlayer mPlayer;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        player();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY; // 服务的启动必须由明确的调用startService才能生效
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void player() {
        // 1.创建MediaPlayer对象，此时处于idle状态
        mPlayer = new MediaPlayer();
        // 充值，使MediaPlayer重回idle状态
//        mPlayer.reset();

        try {
            // 2.设置播放器，Initialized状态
            // 设置SDCard下面、网络中的音乐
            mPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/victoria.mp3");
            // 3.进入准备状态
            mPlayer.prepare();
            // 4.播放音乐
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.start();
            mPlayer.release();
        }
    }
}
