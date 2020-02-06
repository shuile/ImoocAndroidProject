package cn.shui.blesample;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BluetoothAdapter mBluetoothAdapter;
    private Toast mToast;
    private Button mBtScan;
    private boolean mIsScanStart = false;
    private BluetoothLeScanner mLeScanner;
    private ScanSettings mScanSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (mBluetoothAdapter != null) {
            showToast("手机支持蓝牙功能");
        } else {
            finish();
        }

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            showToast("手机不支持蓝牙BLE功能");
            finish();
        } else {
            showToast("手机支持蓝牙BLE功能");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
            mScanSettings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .setReportDelay(3000)
                    .build();
        }

        mBtScan = findViewById(R.id.scan_btn);
        mBtScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsScanStart) {
                    mBtScan.setText("停止扫描");
                    mIsScanStart = true;
                    scan(true);
                } else {
                    mBtScan.setText("开始扫描");
                    mIsScanStart = false;
                    scan(false);
                }
            }
        });
    }

    @TargetApi(23)
    private void scan(boolean enable) {
        final ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                BluetoothDevice device = result.getDevice();
                Log.d(TAG, "name = " + device.getName() + ", address = " + device.getAddress());
            }
        };
        if (enable) {
            mLeScanner.startScan(scanCallback);
        } else {
            mLeScanner.stopScan(scanCallback);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            //申请打开蓝牙功能
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
        }
    }

    private void showToast(String msg) {
        mToast.setText(msg);
        mToast.show();
    }

    private void requestPermission() {
        if (PackageManager.PERMISSION_GRANTED ==
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }
    }
}
