package cn.shui.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-vv";

    // IBinder
    // ServiceConnection:用于绑定客服端和服务的
    // 进度监控
    private ServiceConnection conn = new ServiceConnection() {
        // 当客户端正常连接着服务时，执行服务的绑定操作会被调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");

            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                iMyAidlInterface.showProgress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

//            MyService.MyBinder myBinder = (MyService.MyBinder) service;
//            Log.d(TAG, "onServiceConnected: process is " + myBinder.getProcess());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operate(View view) {
        switch (view.getId()) {
            case R.id.start:
                // 启动服务:创建-->启动-->销毁
                // 如果服务已经创建了，后续重复启动，操作的都是同一个服务，不会再重新创建了，除非你先销毁它
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop:
                // 停止服务
                Intent intent1 = new Intent(this, MyService.class);
                stopService(intent1);
                break;
            case R.id.bind:
                // 绑定服务:最大的作用是用来实现对Service执行的任务进行进度监控
                // 如果服务不存在：onCreate-->onBind-->onUnbind-->onDestroy
                // (此时服务没有在后台运行，并且它会随着Activity的摧毁而解绑并销毁)
                // 服务已经存在：那么bindService方法只能使onBind方法被调用，而unbindService方法只能使onUnbind被调用
                Intent intent2 = new Intent(this, MyService.class);
                bindService(intent2, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                // 解绑服务
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}
