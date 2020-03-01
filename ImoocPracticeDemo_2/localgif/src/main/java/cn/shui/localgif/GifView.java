package cn.shui.localgif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

/**
 * Created by shui on 2020-02-17
 */
public class GifView extends FrameLayout {

    private ImageView mImageView;
    private String[] mImages;
    private int mEndIndex;
    private int mRate = 1000;

    public GifView(@NonNull Context context) {
        super(context);

        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(mImageView);
    }

    public void setRate(int mRate) {
        this.mRate = mRate;
    }

    public void setImages(String[] images) {
        mImages = images;
        mEndIndex = mImages.length - 1;

        Bitmap bitmap = BitmapFactory.decodeFile(mImages[0]);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        mImageView.setLayoutParams(params);
        bitmap.recycle();
    }

    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (index < mEndIndex) {
                    long stime = System.currentTimeMillis();
                    Message message = Message.obtain();
                    message.obj = BitmapFactory.decodeFile(mImages[index]);
                    mHandler.sendMessage(message);
                    long interval = System.currentTimeMillis() - stime;
                    long offset = mRate - interval;

                    if (offset > 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    index++;
                    if (index == mEndIndex) {
                        index = 0;
                    }
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            mImageView.setImageBitmap((Bitmap) msg.obj);
        }
    };
}
