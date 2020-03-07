package cn.shui.surfaceview.game;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by shui on 2020-03-07
 */
public class Utils {

    public static int dp2px(Context context, int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                context.getResources().getDisplayMetrics());
    }
}
