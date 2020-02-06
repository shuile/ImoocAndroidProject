package cn.imooc.ui_seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        //设置进度条最大值
        seekBar.setMax(100);
        //设置进度条当前值
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //将在进度变化时被触发
                /*
                 * 1、当前绑定的seekBar对象
                 * 2、当前进度数值
                 * 3、是否为用户手动触发
                 */
                Log.e(TAG, seekBar.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //将在开始拖拽进度条时被触发
                Log.e(TAG, seekBar.getProgress() + "");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //将在结束拖拽进度条时被触发
                Log.e(TAG, seekBar.getProgress() + "");
            }
        });
    }
}
