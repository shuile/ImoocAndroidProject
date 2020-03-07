package cn.shui.surfaceview.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by shui on 2020-03-07
 */
public class Bird extends DrawablePart {

    private int x;
    private int y;

    private static final float RADIO_Y_POS = 1 / 2F;

    // 30dp
    private static final int WIDTH_BIRD = 30;

    private int mWidth;
    private int mHeight;

    private RectF mRectF = new RectF();

    public Bird(Context context, int gameW, int gameH, Bitmap bitmap) {
        super(context, gameW, gameH, bitmap);

        y = (int) (gameH * RADIO_Y_POS);
        mWidth = Utils.dp2px(context, WIDTH_BIRD);
        mHeight = (int) (mWidth * 1.0 / bitmap.getWidth() * bitmap.getHeight());

        x = gameW / 2 - mWidth / 2;
    }

    @Override
    public void draw(Canvas canvas) {
        mRectF.set(x, y, x + mWidth, y + mHeight);
        canvas.drawBitmap(mBitmap, null, mRectF, null);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void reset() {
        y = (int) (mGameHeight * RADIO_Y_POS);
    }
}
