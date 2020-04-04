package cn.shui.audiodemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    private MediaPlayer mPlayer;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        } else {
            Log.d(TAG, "onCreate: ");
            // 启动服务
            intent = new Intent(this, MyService.class);
            startService(intent);
        }
    }

//    private void player() {
//        // 1.创建MediaPlayer对象，此时处于idle状态
//        mPlayer = new MediaPlayer();
//        // 充值，使MediaPlayer重回idle状态
////        mPlayer.reset();
//
//        try {
//            // 2.设置播放器，Initialized状态
//            // 设置SDCard下面、网络中的音乐
//            mPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/victoria.mp3");
//            // 3.进入准备状态
//            mPlayer.prepare();
//            // 4.播放音乐
//            mPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void player2() {
//        // 参数2：播放源，存放于资源文件夹中
//        mPlayer = MediaPlayer.create(this, R.raw.victoria);
//        // 通过create方法创建的MediaPlayer对象不需要再调用prepare方法了
//        // 如果再调用，会引发IllegalStateException
//        mPlayer.start();
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish();
            } else {
//                player2();
                if (intent == null) {
                    intent = new Intent(this, MyService.class);
                }
                startService(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);
        }
    }

    public void voiceControl(View view) {
        // 1.获取声音管理器
        AudioManager manager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        if (manager == null) {
            Toast.makeText(this, "AudioManager not support", Toast.LENGTH_SHORT).show();
            return;
        }
        // 2.操作
        switch (view.getId()) {
            case R.id.up:
                // 参数1：声音类型
//                AudioManager.STREAM_ALARM 警报 闹钟
//                AudioManager.STREAM_MUSIC 媒体
//                AudioManager.STREAM_NOTIFICATION 通知
//                AudioManager.STREAM_RING 铃声
//                AudioManager.STREAM_VOICE_CALL 通话
//                AudioManager.STREAM_SYSTEM 系统
                // 参数2：调整方向，增加/减少
//                AudioManager.ADJUST_RAISE
//                AudioManager.ADJUST_LOWER
//                AudioManager.ADJUST_SAME
                // 参数3：FLAG_PLAY_SOUND 播放声音 FLAG_SHOW_UI 出现音量条 0 什么也没有
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                break;
            case R.id.down:
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                break;
            case R.id.mute:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // API >= 23 手机版本 > 6.0
                    // IlleagalArgumentException: Bad deriction -100
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
                } else {
                    // API < 23
                    manager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                }
                break;
            case R.id.unmute:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // API >= 23 手机版本 > 6.0
                    // IlleagalArgumentException: Bad deriction -100
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
                } else {
                    // API < 23
                    manager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                }
                break;
        }
    }
}
