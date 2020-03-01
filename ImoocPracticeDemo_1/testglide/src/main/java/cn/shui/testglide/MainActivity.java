package cn.shui.testglide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv_glide);
    }

    public void load(View view) {
        RequestBuilder<Drawable> drawableRequestBuilder = Glide.with(this).load("http://youimg1.c-ctrip.com/target/tg/096/755/666/49611e232c4646bcbfdca563a39b15ab.jpg");
        Glide.with(this)
                .load("http://imgk.zol.com.cn/dcbbs/8952/a8951705.jpg")
                .thumbnail(drawableRequestBuilder)
                .error(R.drawable.f029)
                .transition(withCrossFade())
                .into(imageView);
    }
}
