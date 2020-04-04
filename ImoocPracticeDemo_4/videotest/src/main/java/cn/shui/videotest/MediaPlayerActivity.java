package cn.shui.videotest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.io.File;
import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {

    private RelativeLayout mRelativeLayout;
    private SurfaceView mSurfaceView;
    private SeekBar mSeekBar;
    private Button mBtnPlay;
    private MediaPlayer mMediaPlayer;

    private boolean mIsPause;

    private float mRarioHW;

    private Handler mHandler = new Handler();
    private Runnable mUpdateProgressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mMediaPlayer == null) {
                return;
            }
            int currentPosition = mMediaPlayer.getCurrentPosition();
            int duration = mMediaPlayer.getDuration();

            if (mSeekBar != null && duration > 0) {
                int progress = (int) (currentPosition * 1.0f / duration * 1000);
                mSeekBar.setProgress(progress);

                if (mMediaPlayer.isPlaying()) {
                    mHandler.postDelayed(mUpdateProgressRunnable, 1000);
                }
            }
        }
    };

    private boolean mIsPrepared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_media_player);

        initView();

        initMediaPlayer();

        initEvents();
    }

    private void initEvents() {
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mMediaPlayer.setDisplay(holder);

                if (!mIsPrepared) {
                    return;
                }

                if (mIsPause) {
                    mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition());
                    return;
                }

                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                    mHandler.post(mUpdateProgressRunnable);
                    mBtnPlay.setText("暂停");
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mUpdateProgressRunnable);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                long duration = mMediaPlayer.getDuration();
                int targets = (int) (seekBar.getProgress() * 1.0f / 1000 * duration);
                mMediaPlayer.seekTo(targets);

                if (mMediaPlayer.isPlaying()) {
                    mHandler.post(mUpdateProgressRunnable);
                }
            }
        });

        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer == null) {
                    return;
                }

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mBtnPlay.setText("播放");
                    mHandler.removeCallbacks(mUpdateProgressRunnable);
                    mIsPause = true;
                } else {
                    mMediaPlayer.start();
                    mBtnPlay.setText("暂停");
                    mHandler.post(mUpdateProgressRunnable);
                    mIsPause = false;
                }
            }
        });
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "lalala.mp4");
        try {
            mMediaPlayer.setDataSource(file.getAbsolutePath());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mIsPrepared = true;
                    mBtnPlay.setEnabled(true);
                    if (!mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start();
                        mBtnPlay.setText("暂停");
                        mHandler.post(mUpdateProgressRunnable);
                    }
                }
            });
            mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    ViewGroup.LayoutParams lp = mRelativeLayout.getLayoutParams();
                    mRarioHW = height * 1.0f / width;
                    lp.height = (int) (mRelativeLayout.getWidth() * 1.0f / width * height);
                    mRelativeLayout.setLayoutParams(lp);
                }
            });

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mHandler.removeCallbacks(mUpdateProgressRunnable);
                    mSeekBar.setProgress(1000);
                    mBtnPlay.setText("播放");
                    mIsPause = true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
        mHandler.removeCallbacks(mUpdateProgressRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mRelativeLayout = findViewById(R.id.rl_container);
        mSurfaceView = findViewById(R.id.surface_view);
        mSeekBar = findViewById(R.id.seekbar);
        mBtnPlay = findViewById(R.id.btn_play);
        mSeekBar.setMax(1000);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewTreeObserver observer = mRelativeLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                ViewGroup.LayoutParams lp = mRelativeLayout.getLayoutParams();
                lp.height = (int) (mRarioHW * mRelativeLayout.getWidth());
                mRelativeLayout.setLayoutParams(lp);
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MediaPlayerActivity.class);
        context.startActivity(intent);
    }
}
