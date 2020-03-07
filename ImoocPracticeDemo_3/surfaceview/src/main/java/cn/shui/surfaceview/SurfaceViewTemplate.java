package cn.shui.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by shui on 2020-03-07
 */
public class SurfaceViewTemplate extends SurfaceView implements Runnable {

    private Thread mThread;
    private volatile boolean isRunning;

    private Paint mPaint;

    private int mMinRadius;
    private int mMaxRadius;
    private int mRadius;
    private int mFlag;

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // 监听Surface创建完毕
                isRunning = true;
                mThread = new Thread(SurfaceViewTemplate.this);
                mThread.start();
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

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mMaxRadius = Math.min(w, h) / 2 - 20;
        mRadius = mMinRadius = 30;
    }

    @Override
    public void run() {
        while (isRunning) {
            drawSelf();
        }
    }

    private void drawSelf() {
        Canvas canvas = getHolder().lockCanvas();

        try {
            if (canvas != null) {
                // canvas.draw
                drawCircle(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);

        if (mRadius >= mMaxRadius) {
            mFlag = -1;
        } else if (mRadius <= mMinRadius) {
            mFlag = 1;
        }

        mRadius += mFlag * 2;
    }
}
