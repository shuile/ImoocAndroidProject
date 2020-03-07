package cn.shui.surfaceview.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.DrawableRes;

import cn.shui.surfaceview.R;

/**
 * Created by shui on 2020-03-07
 */
public class FlappyBird extends SurfaceView implements Runnable {

    private Thread mThread;
    private volatile boolean isRunning;

    private RectF mDestRect;

    // res
    private Bitmap mBg;
    private Bitmap mBirdBm;
    private Bitmap mFloorBm;

    private Floor mFloor;
    private Bird mBird;

    private int mSpeed;


    private static final int TOUCH_UP_SIZE = -16;
    private int mBirdUpDis;

    private static final int SIZE_AUTO_DOWN = 2;
    private int mAutoDownDis;

    private int mTmpBirdDis;

    public FlappyBird(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // 监听Surface创建完毕
                isRunning = true;
                mThread = new Thread(FlappyBird.this);
                mThread.start();

                mBird.reset();
                mTmpBirdDis = 0;
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                isRunning = false;
            }
        });

        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

        mSpeed = Utils.dp2px(getContext(), 2);
        mBirdUpDis = Utils.dp2px(getContext(), TOUCH_UP_SIZE);
        mAutoDownDis = Utils.dp2px(getContext(), SIZE_AUTO_DOWN);
        initRes();
    }

    private void initRes() {
        mBg = loadBitmapByResId(R.drawable.bg1);
        mBirdBm = loadBitmapByResId(R.drawable.b1);
        mFloorBm = loadBitmapByResId(R.drawable.floor_bg);
    }

    private Bitmap loadBitmapByResId(@DrawableRes int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDestRect = new RectF(0, 0, w, h);

        mFloor = new Floor(getContext(), w, h, mFloorBm);
        mBird = new Bird(getContext(), w, h, mBirdBm);
    }

    @Override
    public void run() {
        while (isRunning) {
            long start = System.currentTimeMillis();
            drawSelf();
            long end = System.currentTimeMillis();

            if ((end - start) < 50) {
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void drawSelf() {
        Canvas canvas = getHolder().lockCanvas();

        try {
            if (canvas != null) {
                // canvas.draw

                drawBg(canvas);

                logic();

                drawBird(canvas);
                drawFloor(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    private void logic() {
        mFloor.setX(mFloor.getX() - mSpeed);

        mTmpBirdDis += mAutoDownDis;
        mBird.setY(mBird.getY() + mTmpBirdDis);
    }

    private void drawBird(Canvas canvas) {
        mBird.draw(canvas);
    }

    private void drawFloor(Canvas canvas) {
        mFloor.draw(canvas);
    }

    private void drawBg(Canvas canvas) {
        canvas.drawBitmap(mBg, null, mDestRect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mTmpBirdDis = mBirdUpDis;
        }

        return true;
    }
}
