package com.caxcot.handlerproject;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadActivity extends AppCompatActivity {

    public static final int DOWNLOAD_WHAT = 100001;
    private Handler handler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        /**
         * 主线程-->
         * 点击按键
         * 大气下载
         * 开启子线程做下载
         * 下载过程中通知主线程
         * 主线程更新进度条
         */

        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download("http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk");
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case DOWNLOAD_WHAT:
                        progressBar.setProgress((Integer) msg.obj);
                        break;
                }
            }
        };
    }

    private void download(String appUrl) {
        try {
            URL url = new URL(appUrl);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            /**
             * 获取文件的总长度
             */
            int contentLength = urlConnection.getContentLength();

            String downloadFolderName = Environment.getExternalStorageDirectory()
                    + File.separator + "imooc" + File.separator;
            File file= new File(downloadFolderName);
            if (!file.exists()) {
                file.mkdirs();
            }

            String fileName = downloadFolderName + "imooc.apk";
            File apkFile = new File(fileName);

            if (!apkFile.exists()) {
                apkFile.mkdirs();
            }

            int downloadSize = 0;

            byte[] bytes = new byte[1024];
            int length = 0;
            OutputStream outputStream = new FileOutputStream(fileName);
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                downloadSize += length;

                /**
                 * update UI
                 */

                Message message = Message.obtain();
                message.obj = downloadSize * 100 / contentLength;
                message.what = DOWNLOAD_WHAT;
                handler.sendMessage(message);
            }
            inputStream.close();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
