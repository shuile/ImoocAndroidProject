package cn.shui.udpdemo.biz;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by shui on 2020-01-19
 */
public class TcpClientBiz {

    private Socket mSocket;
    private InputStream mIs;
    private OutputStream mOs;

    private Handler mUiHandler = new Handler(Looper.getMainLooper());

    public interface OnMsgComingListener {
        void onMsgComing(String msg);

        void onError(Exception e);
    }

    private OnMsgComingListener mListener;

    public void setOnMsgComingListener(OnMsgComingListener mListener) {
        this.mListener = mListener;
    }

    public TcpClientBiz() {

        new Thread() {
            @Override
            public void run() {
                try {
                    mSocket = new Socket("192.168.43.87", 11111);
                    mIs = mSocket.getInputStream();
                    mOs = mSocket.getOutputStream();

                    readServerMsg();

                } catch (final IOException e) {
                    mUiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mListener != null) {
                                mListener.onError(e);
                            }
                        }
                    });
                }
            }
        }.start();
    }

    private void readServerMsg() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(mIs));

        String line = null;
        while ((line = br.readLine()) != null) {
            final String finalLine = line;
            mUiHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mListener != null) {
                        mListener.onMsgComing(finalLine);
                    }
                }
            });
        }
    }

    public void sendMsg(final String msg) {
        new Thread() {
            @Override
            public void run() {
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(mOs));
                    bw.write(msg);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void onDestroy() {
        if (mIs != null) {
            try {
                mIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mOs != null) {
            try {
                mOs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mSocket != null) {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
