package cn.shui.animation.property;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

import cn.shui.animation.R;

public class PropertyActivity extends AppCompatActivity {

    private static final String TAG = "PropertyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
    }

    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_value_animator:
////                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
//                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1.0f);
//                valueAnimator.setDuration(100);
//                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        float animatedFraction = animation.getAnimatedFraction();
//                        float animatedValue = (float) animation.getAnimatedValue();
//                        Log.d(TAG, "onAnimationUpdate: " + String.format("%.3f   %.3f", animatedFraction, animatedValue));
//                    }
//                });
//                valueAnimator.start();
//                break;
            case R.id.tv_view_alpha_animation:
                Animator alphaAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha);
                alphaAnimator.setTarget(view);
                alphaAnimator.start();
                break;
            case R.id.tv_view_scale_animation:
                ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 3.0f).start();
                break;
            case R.id.tv_view_translate_animation:
//                ViewPropertyAnimator translateAnimator = view.animate();
//                translateAnimator.translationX(500f);
//                translateAnimator.start();
                view.animate().translationX(500f).setDuration(1000).start();
                break;
            case R.id.tv_view_rotate_animation:

                break;
            case R.id.tv_view_set_animation:
//                Animator rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 720);
//                rotateAnimator.setDuration(1000);
//
//                Animator moveAnimator = ObjectAnimator.ofFloat(view, "x", 0, 500);
//                moveAnimator.setDuration(1000);
//
//                AnimatorSet set = new AnimatorSet();
////                set.playTogether(rotateAnimator, moveAnimator);
//                set.playSequentially(rotateAnimator, moveAnimator);
//                set.start();


                view.animate().rotation(720).setDuration(1000).start();
                view.animate().translationX(500).setDuration(1000).setStartDelay(1000).start();
                break;
            default:
                break;
        }
    }
}
