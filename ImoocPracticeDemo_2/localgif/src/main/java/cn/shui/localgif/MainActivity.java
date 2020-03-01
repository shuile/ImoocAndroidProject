package cn.shui.localgif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GifView gifView = new GifView(this);
        ((ViewGroup) findViewById(R.id.layout_holder)).addView(gifView);
        File dir = new File("/storage/emulated/0/images");
        File[] files = dir.listFiles();
        String[] images = new String[files.length];
        for (int i = 0; i < images.length; i++) {
            images[i] = files[i].getAbsolutePath();
        }
        gifView.setImages(images);
        gifView.setRate(200);
        gifView.play();
    }
}
