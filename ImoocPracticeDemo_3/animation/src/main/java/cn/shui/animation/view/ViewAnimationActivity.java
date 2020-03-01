package cn.shui.animation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import cn.shui.animation.R;

public class ViewAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.renew, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.renew:
                recreate();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_view_alpha_animation:
                Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                view.startAnimation(alphaAnimation);
                break;
            case R.id.tv_view_scale_animation:
                Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                view.startAnimation(scaleAnimation);
                break;
            case R.id.tv_view_translate_animation:
                Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                view.startAnimation(translateAnimation);
                break;
            case R.id.tv_view_rotate_animation:
                Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                view.startAnimation(rotateAnimation);
                break;
            case R.id.tv_view_set_animation:
                Animation setAnimation = AnimationUtils.loadAnimation(this, R.anim.set);
                view.startAnimation(setAnimation);
                break;
            case R.id.tv_view_accelerate:
            case R.id.tv_view_linear:
                View viewLinear = findViewById(R.id.tv_view_linear);
                View viewAccelerate = findViewById(R.id.tv_view_accelerate);

                Animation animationLinear = AnimationUtils.loadAnimation(this, R.anim.translate);
                Animation animationAccelerate = AnimationUtils.loadAnimation(this, R.anim.translate);
                animationLinear.setInterpolator(new LinearInterpolator());
                animationAccelerate.setInterpolator(new AccelerateInterpolator());

                viewLinear.startAnimation(animationLinear);
                viewAccelerate.startAnimation(animationAccelerate);
                break;
            default:
                break;
        }
    }
}
