package cn.shui.aidldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import cn.shui.servicedemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                iMyAidlInterface.showProgress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

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
                // 远程启动服务
                Intent intent = new Intent("cn.shui.myservice");
                intent.setPackage("cn.shui.servicedemo");
                startService(intent);
                break;
            case R.id.end:
                intent = new Intent("cn.shui.myservice");
                intent.setPackage("cn.shui.servicedemo");
                stopService(intent);
                break;
            case R.id.bind:
                Log.d(TAG, "operate: ");
                intent = new Intent("cn.shui.myservice");
                intent.setPackage("cn.shui.servicedemo");
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}
