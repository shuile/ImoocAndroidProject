package cn.imooc.ui_base_progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btn01;
    private Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        progressBar.setMax(100);
        progressBar.setProgress(30);
        progressBar.setSecondaryProgress(40);
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        btn01 = (Button) findViewById(R.id.btn_01);
        btn02 = (Button) findViewById(R.id.btn_02);
        ButtonListener buttonListener = new ButtonListener();
        btn01.setOnClickListener(buttonListener);
        btn02.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_01:
                    progressBar.incrementProgressBy(20);
                    break;
                case R.id.btn_02:
                    progressBar.incrementSecondaryProgressBy(40);
                    break;
            }
        }
    }
}
