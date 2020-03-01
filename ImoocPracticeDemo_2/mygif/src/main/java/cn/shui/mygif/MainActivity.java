package cn.shui.mygif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GifView gifView = new GifView(this);
        ((ViewGroup) findViewById(R.id.layout_holder)).addView(gifView);
        gifView.setMovieResource(R.raw.ppt);
    }
}
