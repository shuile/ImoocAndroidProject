package cn.shui.mygif;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;

import java.io.File;

/**
 * Created by shui on 2020-02-17
 */
public class GifView extends View {

    private int mResId;
    private Movie mMovie;
    private long mStartTime;

    public GifView(Context context) {
        super(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setMovieResource(int resId) {
        mResId = resId;
        mMovie = Movie.decodeStream(getResources().openRawResource(resId));
    }

    public void setFile(File file) {
        mMovie = Movie.decodeFile(file.getAbsolutePath());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMovie != null) {
            setMeasuredDimension(mMovie.width(), mMovie.height());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mMovie != null) {
            long now = SystemClock.uptimeMillis();

            if (mStartTime == 0) {
                mStartTime = now;
            }

            int dur = mMovie.duration();

            if (dur == 0) {
                dur = 1000;
            }

            int time = (int) ((now - mStartTime) % dur);
            mMovie.setTime(time);
            mMovie.draw(canvas, 0, 0);
            invalidate();
        }
    }
}
