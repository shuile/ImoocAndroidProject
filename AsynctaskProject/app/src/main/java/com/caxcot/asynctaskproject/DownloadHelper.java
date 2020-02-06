package com.caxcot.asynctaskproject;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 1.download方法 url localPath mListener
 * 2.mListener：start，success fail progress
 * 3.用asyncTask封装的
 */
public class DownloadHelper {

    public static void download(String url, String localPath, OnDownloadListener listener) {
        DownloadAsyncTask task = new DownloadAsyncTask(url, localPath, listener);
        task.execute();
    }

    public static class DownloadAsyncTask extends AsyncTask<String, Integer, Boolean> {

        String mUrl;
        String mFilePath;
        OnDownloadListener mListener;

        public DownloadAsyncTask(String mUrl, String mFilePath, OnDownloadListener listener) {
            this.mUrl = mUrl;
            this.mFilePath = mFilePath;
            this.mListener = listener;
        }

        /**
         * 在异步任务之前，在主线程中
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //可操作UI
            if (mListener != null) {
                mListener.onStart();
            }
        }

        /**
         * 在另外一个线程中处理事件
         * @param strings 入参 煮米
         * @return 结果
         */
        @Override
        protected Boolean doInBackground(String... strings) {
                String apkUrl = strings[0];
                try {
                    //构造URL
                    URL url = new URL(apkUrl);
                    //构造连接，并打开
                    URLConnection urlConnection = url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();

                    //获取了下载内容的总长度
                    int contentLength = urlConnection.getContentLength();

                    //对下载地址进行处理
                    File apkFile = new File(mFilePath);
                    if (apkFile.exists()) {
                        boolean result = apkFile.delete();
                        if (!result) {
                            if (mListener != null) {
                                mListener.onFail(-1, apkFile, "文件删除失败");
                            }
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
                    if (mListener != null) {
                        mListener.onFail(-2, new File(mFilePath), e.getMessage());
                    }
                    return false;
                }
                if (mListener != null) {
                    mListener.onSuccess(0, new File(mFilePath));
                }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //也是在主线程中，执行结果 处理
            if (mListener != null) {
                if (aBoolean) {
                    mListener.onSuccess(0, new File(mFilePath));
                } else {
                    mListener.onFail(-1, new File(mFilePath), "下载失败");
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //收到进度，然后处理：  也是在UI线程
            if (values != null && values.length > 0) {
                if (mListener != null) {
                    mListener.onProgress(values[0]);
                }
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

    public interface OnDownloadListener {
        void onStart();

        void onSuccess(int code, File file);

        void onFail(int code, File file, String message);

        void onProgress(int progress);

        abstract class SimpleDownloadListener implements OnDownloadListener {
            @Override
            public void onStart() {

            }

            @Override
            public void onProgress(int progress) {

            }
        }
    }
}
