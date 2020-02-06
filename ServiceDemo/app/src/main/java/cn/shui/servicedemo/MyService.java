package cn.shui.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService-vv";
    private int i;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        // 开启一个线程(从1数到100)，用于模拟耗时的任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 1; i <= 100; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    // 绑定
    // IBinder:在Android中用于远程操作对象的一个基本接口
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: ");
        // Binder
        return new IMyAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void showProgress() throws RemoteException {
                Log.d(TAG, "showProgress: 当前进度是" + i);
            }
        };
    }

    // 对于onBind方法而言，要求返回IBinder对象
    // 实际上，我们会自己定义一个内部类，集成Binder类

    class MyBinder extends Binder {
        // 定义自己需要的方法（实现进度监控）
        public int getProcess() {
            return i;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
