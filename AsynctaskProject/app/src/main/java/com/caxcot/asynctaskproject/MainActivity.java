package com.caxcot.asynctaskproject;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 1.网络请求数据：申请网络权限、读写存储权限
 * 2.布局我们的layot
 * 3.下载前我们要做什么？  UI
 * 4.下载中我们要做什么？  数据
 * 5.下载后我们要做什么？  UI
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String APK_URL = "http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";
    public static final String FILE_NAME = "imooc.apk";
    private ProgressBar mProgressBar;
    private Button mDownloadButton;
    private TextView mResultTextView;
    private int INIT_PROGRESS;
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initView();

        //设置点击监听
        setListener();

        //初始化UI数据
        setData();

        DownloadHelper.download(APK_URL, "", new DownloadHelper.OnDownloadListener.SimpleDownloadListener() {

            @Override
            public void onSuccess(int code, File file) {

            }

            @Override
            public void onFail(int code, File file, String message) {

            }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mDownloadButton = (Button) findViewById(R.id.button);
        mResultTextView = (TextView) findViewById(R.id.textView);
    }

    private void setListener() {
        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019/3/18 下载任务
                DownloadAsyncTask asyncTask = new DownloadAsyncTask();
                asyncTask.execute(APK_URL);
            }
        });
    }

    private void setData() {
        INIT_PROGRESS = 0;
        mProgressBar.setProgress(INIT_PROGRESS);
        //可操作UI 类似淘米，之前的准备工作
        mDownloadButton.setText(R.string.click_download);
        mResultTextView.setText(R.string.download_text);
    }

    /**
     * String 入参
     * Integer 进度
     * Boolean 返回值
     */
    public class DownloadAsyncTask extends AsyncTask<String, Integer, Boolean> {

        /**
         * 在异步任务之前，在主线程中
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           //可操作UI
            mDownloadButton.setText(R.string.downloading);
            mResultTextView.setText(R.string.downloading);
            mProgressBar.setProgress(INIT_PROGRESS  );
        }

        /**
         * 在另外一个线程中处理事件
         * @param strings 入参 煮米
         * @return 结果
         */
        @Override
        protected Boolean doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                String apkUrl = strings[0];
                try {
                    //构造URL
                    URL url = new URL(apkUrl);
                    //构造连接，并打开
                    URLConnection urlConnection = url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    //获取了下载内容的总长度
                    int contentLength = urlConnection.getContentLength();

                    //下载地址准备
                    mFilePath = Environment.getExternalStorageDirectory() + File.separator
                            + FILE_NAME;

                    //对下载地址进行处理
                    File apkFile = new File(mFilePath);
                    if (apkFile.exists()) {
                        boolean result = apkFile.delete();
                        if (!result) {
                            return false;
                        }
                    }

                    //已下载的大小
                    int downloadSize = 0;

                    byte[] bytes = new byte[1024];

                    int length;

                    //创建一个输入管道
                    OutputStream outputStream = new FileOutputStream(mFilePath);

                    //不断的一车一车挖土，直到挖不到为止
                    while ((length = inputStream.read(bytes)) != -1) {
                        //挖到的放到我们的文件管道里
                        outputStream.write(bytes, 0, length);
                        //累加我们的大小
                        downloadSize += length;
                        //发送进度
                        publishProgress(downloadSize * 100 / contentLength);
                    }

                    inputStream.close();
                    outputStream.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //也是在主线程中，执行结果 处理
            mDownloadButton.setText(aBoolean ? getString(R.string.download_finish) : getString(R.string.download_fail));
            mResultTextView.setText(aBoolean ? getString(R.string.download_finish) + mFilePath : getString(R.string.download_fail));

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //收到进度，然后处理：  也是在UI线程
            if (values != null && values.length > 0) {
                mProgressBar.setProgress(values[0]);
            }
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
            //
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
