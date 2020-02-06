package cn.shui.imoocbutterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    TextView mTv;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;

    @BindString(R.string.app_name)
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mTv.setText(str);

        mBtn1.setText("Hello");
        mBtn2.setText("World");
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Toast.makeText(this, "Btn1 Clicked.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this, "Btn2 Clicked.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
