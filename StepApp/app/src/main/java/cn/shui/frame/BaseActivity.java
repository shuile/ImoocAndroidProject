package cn.shui.frame;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by shui on 2020/5/11
 */
public abstract class BaseActivity extends FragmentActivity {

    // 是否显示程序标题
    protected boolean isHideAppTitle = true;
    // 是否显示系统标题
    protected boolean isHideSysTitle = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.onInitVariable();
        if (isHideAppTitle) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);
        if (isHideSysTitle) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        // 构造View，绑定事件
        this.onInitView(savedInstanceState);
        this.onRequestData();
        FrameApplication.addToActivityList(this);
    }

    @Override
    protected void onDestroy() {
        FrameApplication.removeFromActivityList(this);
        super.onDestroy();
    }

    /**
     * 1) 初始化变量 最闲被调用 用于初始化一些变量，创建一些对象
     */
    protected abstract void onInitVariable();

    /**
     * 2) 初始化UI 布局载入操作
     */
    protected abstract void onInitView(final Bundle savedInstanceState);

    /**
     * 请求数据
     */
    protected abstract void onRequestData();
}
