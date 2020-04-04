package cn.shui.recordvoicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

// 按下按钮，开始录制（文本会变为"请大声说话"）
// 松开按钮，停止录制（文本复原），播放刚刚录制的声音
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button recordButton;
    private MediaRecorder mMediaRecorder; // 录音
    private MediaPlayer mMdeiaPlayer; // 播放声音
    private File voiceFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordButton = findViewById(R.id.record_btn);

        recordButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    // 录音
                    mMediaRecorder = new MediaRecorder();
                    // 设置声音来源
                    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // 设置声音格式, MPEG_4:音频、视频的标准格式
                    mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    // 设置声音编码
                    mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    // 准备文件用于承载录制的声音
                    voiceFile = File.createTempFile("myvoice", "mp3");
                    // 设置声音的输出录音为刚刚准备的好的文件所在的位置
                    Log.d(TAG, "onLongClick: " + voiceFile.getAbsolutePath());
                    mMediaRecorder.setOutputFile(voiceFile.getAbsolutePath());

                    // 准备
                    mMediaRecorder.prepare();
                    //开始录制
                    mMediaRecorder.start();

                    recordButton.setText("请大声说话");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        recordButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // 手指松开，通知录音并播放
                    mMediaRecorder.stop(); // 停止
                    mMediaRecorder.release(); //释放资源

                    recordButton.setText("准备录音");

                    try {
                        // 初始化
                        mMdeiaPlayer = new MediaPlayer();
                        // 设置播放源
                        mMdeiaPlayer.setDataSource(voiceFile.getAbsolutePath());
                        // 准备
                        mMdeiaPlayer.prepare();
                        // 开始播放
                        mMdeiaPlayer.start();

                        mMdeiaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                // 通知播放，释放资源，删除录音文件
                                mMdeiaPlayer.stop();
                                mMdeiaPlayer.release();
                                if (voiceFile.exists()) {
                                    voiceFile.delete();
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }
}
