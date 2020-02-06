package com.caxcot.practiceforget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    public static final int CODE = 111;
    private Button mGetButton;
    private ImageView mGetImageView;

    private MainHandler mHandler = new MainHandler(this);
    public static final String URL_STR = "https://img2.mukewang.com/5adfee7f0001cbb906000338-240-135.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mGetButton = (Button) findViewById(R.id.get_btn);
        mGetImageView = (ImageView) findViewById(R.id.get_iv);
        mGetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_btn:
                new Thread(new GetPicThread()).start();
                break;
            default:
                break;
        }
    }

    public class GetPicThread implements Runnable {

        public static final int CONNECT_TIMEOUT = 30 * 1000;

        @Override
        public void run() {
            try {
                URL url = new URL(URL_STR);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(CONNECT_TIMEOUT);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content_Type", "application/json");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.connect();

                int responseCode = connection.getResponseCode();
                String message = connection.getResponseMessage();
                Log.e(TAG, "responseCode=" + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Message msg = Message.obtain();
                    msg.what = CODE;
                    msg.obj = bitmap;
                    mHandler.sendMessage(msg);
                    inputStream.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public class MainHandler extends Handler {
        final WeakReference<MainActivity> mWeakReference;

        public MainHandler(MainActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    MainActivity mainActivity = mWeakReference.get();
                    mainActivity.mGetImageView.setImageBitmap(bitmap);
                    break;
                default:
                    break;
            }
        }
    }
}
