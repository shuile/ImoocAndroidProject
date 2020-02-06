package com.caxcot.asyncpracticeproject;

import android.os.AsyncTask;

public class DownloadHelper {

    private static final int SUCCESS_CODE = 0;
    private static final int FAIL_CODE = -1;
    private static DownloadAsyncTask task;

    public static void download(OnDownloadListener listener) {
        task = new DownloadAsyncTask(listener);
        task.execute();
    }

    public static void cancel() {
        if (task != null) {
            task.cancel(true);
        }
    }

    private static class DownloadAsyncTask extends AsyncTask<String, Integer, Boolean> {

        OnDownloadListener mListener;

        public DownloadAsyncTask(OnDownloadListener mListener) {
            this.mListener = mListener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mListener != null) {
                mListener.onStart();
            }
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            for (int i = 2; i < 5; i++) {
                if (isCancelled()) {
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    if (mListener != null) {
                        mListener.onFail(FAIL_CODE, e.getMessage());
                    }
                }
                publishProgress(i);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                if (mListener != null) {
                    mListener.onSuccess(SUCCESS_CODE);
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mListener != null) {
                mListener.onProgress(values[0]);
            }
        }
    }

    public interface OnDownloadListener {
        void onStart();

        void onProgress(int progress);

        void onSuccess(int code);

        void onFail(int code, String message);
    }
}
