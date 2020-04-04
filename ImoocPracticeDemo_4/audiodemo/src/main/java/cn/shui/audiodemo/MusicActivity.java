package cn.shui.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private static final String TAG = "MusicActivity";

    private ListView musicLv;
    // 存放所有音乐曲目的名字
    private ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> pathList = new ArrayList<>();
    private int index = 0; // 当前播放的曲目
    private Button playPauseBtn;
    public static ProgressBar musicPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        getMusic("/netease/cloudmusic/Music");
        getMusic("/qqmusic/song");

        musicLv = findViewById(R.id.music_lv);
        playPauseBtn = findViewById(R.id.play_pause);
        musicPro = findViewById(R.id.music_pro);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        musicLv.setAdapter(adapter);

        musicLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                setIndex(index);
            }
        });
    }

    public void getMusic(String path) {
        // 网易云 QQ// netease/cloudmusic/Music
        // qqmusic/song
        path = Environment.getExternalStorageDirectory() + path;
        File f = new File(path);
        if (!f.exists()) {
            Log.d(TAG, "getMusic: file not exist");
            return;
        }
        File[] fs = f.listFiles();
        if (fs == null) {
            Log.d(TAG, "getMusic: fs is null");
            return;
        }
        for (File file : fs) {
            String name = file.getName();
            String stuffix = name.substring(name.length() - 3, name.length());
            if (stuffix.equalsIgnoreCase("mp3")) {
                nameList.add(name);
                pathList.add(file.getAbsolutePath());
            }
        }
    }

    public void control(View view) {
        switch (view.getId()) {
            case R.id.play_pause:
                Intent playPause = new Intent(MusicActivity.this, MusicService.class);
                if (playPauseBtn.getText().equals("▶️")) {
                    // 播放
                    playPause.putExtra("tag", "start");
                    playPauseBtn.setText("||");
                } else {
                    // 暂停
                    playPause.putExtra("tag", "pause");
                    playPauseBtn.setText("▶️");
                }
                startService(playPause);
                break;
            case R.id.stop:
                Intent stop = new Intent(MusicActivity.this, MusicService.class);
                stopService(stop);
                playPauseBtn.setText("▶️");
                break;
            case R.id.last:
                index = (index - 1 + nameList.size()) % nameList.size();
                Log.d(TAG, "control: index minus 1 is " + index);
                setIndex(index);
                break;
            case R.id.next:
                index = (index + 1) % nameList.size();
                setIndex(index);
                break;
        }
    }

    private void setIndex(int index) {
        if (nameList == null || nameList.isEmpty()) {
            return;
        }
        if (index < 0 || index >= nameList.size()) {
            return;
        }
        Intent intent = new Intent(MusicActivity.this, MusicService.class);
        intent.putExtra("index", index);
        intent.putExtra("tag", "play");
        startService(intent);
        playPauseBtn.setText("||");
    }
}
