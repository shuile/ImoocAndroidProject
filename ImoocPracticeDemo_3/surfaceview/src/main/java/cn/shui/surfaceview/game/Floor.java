package cn.shui.surfaceview.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

/**
 * Created by shui on 2020-03-07
 */
public class Floor extends DrawablePart {

    private int x;
    private int y;

    private static final float RADIO_Y_POS = 4 / 5F;

    private Paint mPaint;
    private BitmapShader mBitmapShader;

    public Floor(Context context, int gameW, int gameH, Bitmap bitmap) {
        super(context, gameW, gameH, bitmap);

        y = (int) (gameH * RADIO_Y_POS) ;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
    }

    public void draw(Canvas canvas) {
        canvas.save();

        canvas.translate(x, y);
        mPaint.setShader(mBitmapShader);
        canvas.drawRect(x, 0, -x + mGameWidth, mGameHeight - y, mPaint);

        canvas.restore();

        mPaint.setShader(null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        if (-x > mGameWidth) {
            this.x = x % mGameWidth;
        }
    }
}
