package com.caxcot.dountdowntime;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class DigletActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    public static final int Code = 123;
    public static final int CODE = Code;
    private TextView mResultTextView;
    private ImageView mDigletImageView;
    private Button mStartButton;

    public int[][] mPosition = new int[][] {
            {342, 180}, {432, 880},
            {521, 256}, {429, 780},
            {456, 976}, {145, 665},
            {123, 678}, {564, 567}
    };

    private int mTotalCount;
    private int mSuccessCount;

    public static final int MAX_COUNT = 10;

    private DigletHandler mHandler = new DigletHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diglet);

        initView();
        setTitle("打地鼠");
    }

    private void initView() {
        mResultTextView = findViewById(R.id.textView);
        mDigletImageView = findViewById(R.id.imageView);
        mStartButton = findViewById(R.id.start_button);

        mStartButton.setOnClickListener(this);
        mDigletImageView.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                start();
                break;
        }
    }

    private void start() {
        //发送消息
        mResultTextView.setText("开始啦");
        mStartButton.setText("游戏中");
        mStartButton.setEnabled(false);
        next(0);
    }

    private void next(int delayTime) {
        int position = new Random().nextInt(mPosition.length);

        Message message = Message.obtain();
        message.what = CODE;
        message.arg1 = position;
        mHandler.sendMessageDelayed(message, delayTime);
        mTotalCount++;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.setVisibility(View.GONE);
        mSuccessCount++;
        return false;
    }

    public static class DigletHandler extends Handler {
        public static final int RANDOM_NUMBER = 500;
        public final WeakReference<DigletActivity> mWeakReference;

        public DigletHandler(DigletActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DigletActivity activity = mWeakReference.get();

            switch (msg.what) {
                case CODE:
                    if (activity.mTotalCount > MAX_COUNT) {
                        activity.clear();
                        Toast.makeText(activity, "地鼠打完了", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int position = msg.arg1;
                    activity.mDigletImageView.setX(activity.mPosition[position][0]);
                    activity.mDigletImageView.setY(activity.mPosition[position][1]);
                    activity.mDigletImageView.setVisibility(View.VISIBLE);

                    int randomTime = new Random().nextInt(500) + RANDOM_NUMBER;

                    activity.next(randomTime);
                    break;
            }
        }
    }

    public void clear() {
        mTotalCount = 0;
        mSuccessCount = 0;
        mDigletImageView.setVisibility(View.GONE);
        mStartButton.setText("点击开始");
        mStartButton.setEnabled(true);
    }
}
