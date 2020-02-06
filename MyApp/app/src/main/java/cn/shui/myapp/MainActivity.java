package cn.shui.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.otto.Bus;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: " + getApplication());
        setTitle("MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApp app = (MyApp) getApplication();
        Bus bus = app.getBus();
        Log.d(TAG, "onResume: " + bus);
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApp app = (MyApp) getApplication();
        Bus bus = app.getBus();
        Log.d(TAG, "onPause: " + bus);
        bus.unregister(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGotoActivity2:
                startActivity(new Intent(this, Main2Activity.class));
                break;
            case R.id.btnGotoService:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.btnSetUsername:
                MyApp app = (MyApp) getApplication();
                app.setUsername("iMooc");
                Toast.makeText(MainActivity.this, "Set username to be " + app.getUsername(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnGetUsername:
                app = (MyApp) getApplication();
                Toast.makeText(MainActivity.this, app.getUsername(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
