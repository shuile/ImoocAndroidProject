package cn.shui.imooc_res02.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shui on 2019-12-12
 */
public class T {

    private static Toast toast;

    public static void showToast(String content) {
        toast.setText(content);
        toast.show();
    }

    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }
}
