package cn.shui.transitionanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class SceneActivity extends AppCompatActivity {

    private Scene mOverViewScene;
    private Scene mInfoScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        ViewGroup sceneRoot = findViewById(R.id.scene_root);

        mOverViewScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_overview, getBaseContext());
        mInfoScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_info, getBaseContext());

        TransitionManager.go(mOverViewScene);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInfo:
                Transition transition = TransitionInflater.from(getBaseContext()).inflateTransition(R.transition.transition);
                TransitionManager.go(mInfoScene, transition);
                break;
            case R.id.btnClose:
                TransitionManager.go(mOverViewScene);
                break;
            default:
                break;
        }
    }
}
