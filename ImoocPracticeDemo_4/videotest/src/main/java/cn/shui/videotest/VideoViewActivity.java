package cn.shui.videotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class VideoViewActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private MediaController mMediaController;

    private static final String KEY_CUR_POS = "key_cur_pos";
    private static final String KEY_IS_PAUSE = "key_is_pause";

    private int mCurrentPos;
    private boolean mIsPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        mVideoView = findViewById(R.id.video_view);

        mMediaController = new MediaController(this);

        File file = new File(Environment.getExternalStorageDirectory().getPath(), "lalala.mp4");
        mVideoView.setVideoPath(file.getAbsolutePath());
        mVideoView.setMediaController(mMediaController);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mVideoView.seekTo(mCurrentPos);
        if (!mIsPause) {
            mVideoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentPos = mVideoView.getCurrentPosition();
        mIsPause = !mVideoView.isPlaying();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_CUR_POS, mCurrentPos);
        outState.putBoolean(KEY_IS_PAUSE, mIsPause);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mCurrentPos = savedInstanceState.getInt(KEY_CUR_POS);
        mIsPause = savedInstanceState.getBoolean(KEY_IS_PAUSE);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, VideoViewActivity.class);
        context.startActivity(intent);
    }
}
