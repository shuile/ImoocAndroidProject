package cn.shui.audiodemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * Created by shui on 2020-03-08
 */
public class MusicService extends Service {

    private static final String TAG = "MusicService";

    private MediaPlayer mPlayer;
    private boolean isThreadRunning = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (isThreadRunning) {
                    try {
                        sleep(200);
                        Log.d(TAG, "run: mPlayer is " + mPlayer);
                        if (mPlayer != null) {
                            MusicActivity.musicPro.setProgress(mPlayer.getCurrentPosition());
                        } else {
                            MusicActivity.musicPro.setProgress(0);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private int isStarting = -1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String tag = intent.getStringExtra("tag");
        if (tag.equals("play")) {
            int index = intent.getIntExtra("index", 0);
            play(index);
            isStarting = 1;
        } else if (tag.equals("pause")) {
            mPlayer.pause();
            isStarting = 1;
        } else if (tag.equals("start")) {
            if (isStarting == -1) {
                play(0);
            } else {
                mPlayer.start();
            }
        }
        return START_NOT_STICKY;
    }

    private void play(int index) {
        String path = MusicActivity.pathList.get(index);
        try {
            if (mPlayer.isPlaying()) {
                // 如果音乐正在播放
                mPlayer.stop();
            }
            mPlayer.reset();
            mPlayer.setDataSource(path);
            mPlayer.prepare();
            mPlayer.start();

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // 播放下一首 todo 需要边界值特殊处理,以及将当前index值进行同步
                }
            });

            MusicActivity.musicPro.setMax(mPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            isStarting = -1;
            mPlayer = null;
            isThreadRunning = false;
        }
    }
}
