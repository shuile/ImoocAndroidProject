package cn.shui.recordvoicedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity {

    private static final String TAG = "ChattingActivity";

    private Button voiceBtn;
    private ListView msgList;
    private ChattingAdapter mAdapter;
    private List<Msg> datas = new ArrayList<>();
    private MediaRecorder recorder;
    private List<File> voices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        initPermissions();

//        initData();

        initView();
    }

    private void initPermissions() {
        // 检查程序是否拥有这个权限
//        ContextCompat.checkSelfPermission();
        // 请求权限
//        ActivityCompat.requestPermissions();
        // 是否要向用户解释请求权限的行为
//        ActivityCompat.shouldShowRequestPermissionRationale()

        // 1.获取程序是否具备该项权限
        // 如果是6.0以下的手机，该方法的返回值会始终为PERMISSION_GRANTED
        // 因此你并不需要动态申请权限，直接做你想做的
        // 如果是6.0以上的手机，情况分为两种
        // 如果是normal权限，该方法的返回值会始终为PERMISSION_GRANTED
        // 如果是dangerous权限，动态请求
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        Log.e(TAG, "initPermissions: " + permission);
//        PackageManager.PERMISSION_GRANTED // 权限被授予
//        PackageManager.PERMISSION_DENIED // 权限被拒绝
        // 2.判断，如果不具备权限，则申请权限
        if (permission != PackageManager.PERMISSION_GRANTED) {
            boolean b = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
            Log.d(TAG, "initPermissions: " + b);
            if (b) {
                explainDialog();
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    private void explainDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("录音操作必须要录制音频的权限，是否授权")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 请求权限
                        ActivityCompat.requestPermissions(ChattingActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                    }
                })
                .setNegativeButton("取消", null);
        builder.show();
    }

    private int granted; // 用于保存权限是否被授予的凭证

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                granted = grantResults[0];
            }
        }
    }

    private void initView() {
        voiceBtn = findViewById(R.id.speaker_btn);
        msgList = findViewById(R.id.msg_list);

        mAdapter = new ChattingAdapter(this, datas);
        msgList.setAdapter(mAdapter);

        // 长按按钮，开始录音，松开按钮，录音结束
        // 发送语音的效果
        // 未来在点击了聊天气泡后才播放对应的语音
        voiceBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                voiceBtn.setText("请开始说话");
                try {
                    //  录音
                    // 一系列特性的设置
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    // 指定语音文件
                    File tempFile = File.createTempFile("temp", "mp3");
                    recorder.setOutputFile(tempFile.getAbsolutePath());
                    //保存语音文件
                    voices.add(tempFile);

                    // 开始录音
                    recorder.prepare();
                    recorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        voiceBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 手指按下
                    if (granted != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(ChattingActivity.this, "您不具备录制音频的权限", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                if (granted == PackageManager.PERMISSION_GRANTED && event.getAction() == MotionEvent.ACTION_UP) {
                    voiceBtn.setText("按住说话");
                    // 停止录音
                    recorder.stop();
                    recorder.release();
                    // 更新聊天界面
                    datas.add(new Msg("  ", voices.get(voices.size() - 1), 1));
                    datas.add(new Msg("哈哈哈哈", null, -1)); // 伪造一条跟随者的收到的消息
                    mAdapter.notifyDataSetChanged();
                    msgList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                }
                return false;
            }
        });

        msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Msg msg = datas.get(position);
                try {
                    if (msg.getFlag() == 1) {
                        final MediaPlayer player = new MediaPlayer();
                        player.setDataSource(msg.getFile().getAbsolutePath());
                        player.prepare();
                        player.start();


                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                player.stop();
                                player.release();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        Msg m1 = new Msg("您好", null, 1);
        Msg m2 = new Msg("您也好", null, -1);
        Msg m3 = new Msg("在干嘛", null, 1);
        Msg m4 = new Msg("在看慕课网", null, -1);
        Msg m5 = new Msg("好看吗", null, 1);
        Msg m6 = new Msg("被好看", null, -1);
        Msg m7 = new Msg("推荐个网址被", null, 1);
        Msg m8 = new Msg("www.imooc.com", null, -1);

        datas.add(m1);
        datas.add(m2);
        datas.add(m3);
        datas.add(m4);
        datas.add(m5);
        datas.add(m6);
        datas.add(m7);
        datas.add(m8);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < voices.size(); i++) {
            voices.get(i).delete();
        }
    }
}
