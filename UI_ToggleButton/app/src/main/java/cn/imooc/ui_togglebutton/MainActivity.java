package cn.imooc.ui_togglebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togglebutton);

        toggleButton = (ToggleButton) findViewById(R.id.tb);
        imageView = (ImageView) findViewById(R.id.imageView);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    Toast.makeText(MainActivity.this, toggleButton.getText().toString().trim(), Toast.LENGTH_SHORT)
                            .show();
                    imageView.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Toast.makeText(MainActivity.this, toggleButton.getText().toString().trim(), Toast.LENGTH_SHORT)
                            .show();
                    imageView.setImageResource(R.drawable.ic_launcher_background);
                }
            }
        });
    }
}
