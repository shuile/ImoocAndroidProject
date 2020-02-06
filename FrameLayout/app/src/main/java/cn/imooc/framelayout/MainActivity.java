package cn.imooc.framelayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.frame:
                startActivity(new Intent(this, FrameActivity.class));
                break;
            case R.id.table:
                startActivity(new Intent(this, TableActivity.class));
                break;
            case R.id.grid:
                startActivity(new Intent(this, GridActivity.class));
                break;
            case R.id.constraint:
                startActivity(new Intent(this, ConstraintActivity.class));
                break;
        }
    }
}
