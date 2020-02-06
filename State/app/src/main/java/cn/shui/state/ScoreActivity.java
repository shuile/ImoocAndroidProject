package cn.shui.state;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class ScoreActivity extends AppCompatActivity {

    private static final String TAG = "ScoreActivity-vv";

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment = new ScoreFragment();

        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentByTag("ScoreFragment");

        if (fragment == null) {
            fragment = new ScoreFragment();
            fm.beginTransaction().replace(android.R.id.content, fragment, "ScoreFragment").commit();
        }


        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
