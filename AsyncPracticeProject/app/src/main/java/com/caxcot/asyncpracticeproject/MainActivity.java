package com.caxcot.asyncpracticeproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private TextView mShowTv;
    private Button mStartTaskBtn;
    private Button mCancleTaskBtn;
    private ProgressBar mProgressBar;
    private int init_progress;
    private DownloadHelper.OnDownloadListener mListener = new DownloadHelper.OnDownloadListener() {
        @Override
        public void onStart() {
            mProgressBar.setProgress(25);
            mShowTv.setText("正在下载第1个文件");
        }

        @Override
        public void onProgress(int progress) {
            mProgressBar.setProgress(progress * 25);
            mShowTv.setText("正在下载第" + progress + "个文件");
        }

        @Override
        public void onSuccess(int code) {
            mShowTv.setText("下载完成");
        }

        @Override
        public void onFail(int code, String message) {

        }
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initView();

        //设置监听器
        setListener();

        setData();
    }

    private void initView() {
        mShowTv = (TextView) findViewById(R.id.show_tv);
        mStartTaskBtn = (Button) findViewById(R.id.start_task_btn);
        mCancleTaskBtn = (Button) findViewById(R.id.cancel_task_btn);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_pb);
    }

    private void setListener() {
        mStartTaskBtn.setOnClickListener(this);
        mCancleTaskBtn.setOnClickListener(this);
    }

    private void setData() {
        init_progress = 0;
        mProgressBar.setProgress(init_progress * 25);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_task_btn:
                DownloadHelper.download(mListener);
                break;
            case R.id.cancel_task_btn:
                DownloadHelper.cancel();
                mShowTv.setText("已暂停");
                break;
            default:
                break;
        }
    }
}
