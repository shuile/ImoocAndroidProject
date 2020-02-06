package cn.shui.udpdemo.tcp.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by shui on 2020-01-14
 */
public class MsgPool {
    private static MsgPool sInstance = new MsgPool();

    private LinkedBlockingQueue<String> mQueue = new LinkedBlockingQueue<>();

    private MsgPool() {
    }

    public static MsgPool getInstance() {
        return sInstance;
    }

    public void sendMsg(String msg) {
        try {
            mQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public interface MsgComingListener {
        void onMsgComing(String msg);
    }

    private List<MsgComingListener> mListeners = new ArrayList<>();

    public void addMsgComingListener(MsgComingListener listener) {
        mListeners.add(listener);
    }


    public void start() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String msg = mQueue.take();
                        notifyMsgComing(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void notifyMsgComing(String msg) {
        for (MsgComingListener listener : mListeners) {
            listener.onMsgComing(msg);
        }
    }
}
