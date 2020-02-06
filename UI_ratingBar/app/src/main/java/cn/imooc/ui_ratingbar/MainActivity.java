package cn.imooc.ui_ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //1、当前绑定的ratingBar对象
                //2、当前rating评分的进度
                //3、是否由用户触发
                Log.e("cyt", "当前ratingBar：评分" + rating
                        + "是否来自用户：" + fromUser
                        + "每次评分的刻度：" + ratingBar.getStepSize());
            }
        });
    }
}
