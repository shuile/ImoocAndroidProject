package cn.shui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by shui on 2020-02-24
 */
public class RoundProgressBar extends View {

    private int mRadius;
    private int mColor;
    private int mLineWidth;
    private int mTextSize;
    private int mProgress;

    private Paint mPaint;

    private RectF mProgressCircleRectF;

    public RoundProgressBar(Context context) {
        super(context);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
        mColor = typedArray.getColor(R.styleable.RoundProgressBar_color, 0xffff0000);
        mLineWidth = (int) typedArray.getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3));
        mTextSize = (int) typedArray.getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(32));
        mProgress = typedArray.getInt(R.styleable.RoundProgressBar_android_progress, 30);

        typedArray.recycle();

        initPaint();
    }

    private float dp2px(int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                getResources().getDisplayMetrics());
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int needWidth = measureWidth()+ getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(needWidth, widthSize);
            } else { // MeasureSpec.UNSPECIFIED
                width = needWidth;
            }
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int needHeight = measureHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(needHeight, heightSize);
            } else { // MeasureSpec.UNSPECIFIED
                height = needHeight;
            }
        }
        width = Math.min(width, height);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mProgressCircleRectF = new RectF(0, 0, w - getPaddingLeft() * 2, h  - getPaddingLeft() * 2);
    }

    private int measureHeight() {
        return mRadius * 2;
    }

    private int measureWidth() {

        return mRadius * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth * 1.0f / 4);

        int width = getWidth();
        int height = getHeight();

        canvas.drawCircle(width / 2, height / 2,
                width / 2 - getPaddingLeft() - mPaint.getStrokeWidth() / 2, mPaint);

        mPaint.setStrokeWidth(mLineWidth);

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        float angle = mProgress * 1.0f / 100 * 360;
        canvas.drawArc(mProgressCircleRectF, 0, angle, false, mPaint);
        canvas.restore();

        String text = mProgress + "%";
//        text = "慕课网";
        mPaint.setStrokeWidth(0);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        int y = getHeight() / 2;
        Rect bound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), bound);
        int textHeight = bound.height();
        canvas.drawText(text, 0, text.length(), getWidth() / 2, y + textHeight / 2, mPaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public int getProgress() {
        return  mProgress;
    }

    private static final String INSTANCE = "instance";
    private static final String KEY_PROGRESS = "key_progress";

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PROGRESS, mProgress);
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mProgress = bundle.getInt(KEY_PROGRESS);
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
