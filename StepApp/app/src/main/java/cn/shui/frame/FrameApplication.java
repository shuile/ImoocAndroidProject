package cn.shui.frame;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;

/**
 * Created by shui on 2020/5/5
 */
public class FrameApplication extends Application {

    private static LinkedList<Activity> actList = new LinkedList<Activity>();

    public static LinkedList<Activity> getActList() {
        return actList;
    }

    /**
     * 添加act
     * @param act
     */
    public static void addToActivityList(final  Activity act) {
        if ( act != null) {
            actList.add(act);
        }
    }

    /**
     * 删除act
     * @param act
     */
    public static void removeFromActivityList(final Activity act) {
        if (actList != null && actList.size() > 0 && actList.indexOf(act) != -1) {
            actList.remove(act);
        }
    }

    /**
     * 清理所有Activity
     */
    public static void clearActivityList() {
        for (int i = actList.size() - 1; i >= 0; i--) {
            final Activity act = actList.get(i);
            if (act != null) {
                act.finish();
            }
        }
    }

    /**
     * 退出app
     */
    public static void exitApp() {
        try {
            clearActivityList();
        } catch (Exception e) {

        } finally {
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    private PrefsManager prefsManager;
    private static FrameApplication instance;

    public static FrameApplication getInstance() {
        return instance;
    }

    public PrefsManager getPrefsManager() {
        return prefsManager;
    }

    private ErrorHandler errorHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        prefsManager = new PrefsManager(this);
        errorHandler = ErrorHandler.getInstance();
    }
}
