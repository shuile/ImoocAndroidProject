package cn.shui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by shui on 2020-02-24
 */
public class TextView extends View {

    private String mText = "Imooc";

    private Paint mPaint;

    public TextView(Context context) {
        super(context);
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        boolean booleanTest = typedArray.getBoolean(R.styleable.TextView_test_boolean, false);
        int integerTest = typedArray.getInteger(R.styleable.TextView_test_integer, -1);
        float dimensionTest = typedArray.getDimension(R.styleable.TextView_test_dimension, 0);
        int enumTest = typedArray.getInt(R.styleable.TextView_test_enum, 1);
//        mText = typedArray.getString(R.styleable.TextView_test_string);


        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.TextView_test_string:
                    mText = typedArray.getString(R.styleable.TextView_test_string);
                    break;
                default:
                    break;
            }
        }


        Log.e("TAG", "TextView: " + booleanTest + ", " + integerTest + ", "
                + dimensionTest + ", " + enumTest + ", " + mText);

        typedArray.recycle();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(0xFFFF0000);
        mPaint.setAntiAlias(true);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
            int needWidth = measureWidth() + getPaddingStart() + getPaddingEnd() + getPaddingLeft() + getPaddingRight();
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
        setMeasuredDimension(width, height);
    }

    private int measureHeight() {
        return 0;
    }

    private int measureWidth() {

        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - mPaint.getStrokeWidth() / 2, mPaint);
//        mPaint.setStrokeWidth(1);
//        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
//        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);

        mPaint.setTextSize(72);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        canvas.drawText(mText, 0, mText.length(), 0, getHeight(), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mText = "8888";
        invalidate();
        return true;
    }

    private static final String INSTANCE = "instance";
    private static final String KEY_NEXT = "key_next";

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_NEXT, mText);
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mText = bundle.getString(KEY_NEXT);
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
