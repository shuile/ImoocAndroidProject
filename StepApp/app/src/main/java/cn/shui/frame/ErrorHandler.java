package cn.shui.frame;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Created by shui on 2020/5/5
 */
public class ErrorHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
//        LogWriter.logToFile(true, "Error", "崩溃信息:" + e.getMessage());
//        LogWriter.logToFile(true, "Error", "崩溃线程名称:" + t.getName() + " 线程ID:" + t.getId());
//        final StackTraceElement[] trace = e.getStackTrace();
//        for (final StackTraceElement traceElement : trace) {
//            LogWriter.logToFile(true, "Error", "Lines : " + traceElement.getLineNumber()
//                    + " : " + traceElement.getMethodName());
//        }
        e.printStackTrace();
        FrameApplication.exitApp();
    }

    private static ErrorHandler instance;

    public static ErrorHandler getInstance() {
        if ( ErrorHandler.instance == null) {
            ErrorHandler.instance = new ErrorHandler();
        }
        return ErrorHandler.instance;
    }

    private ErrorHandler() {
    }

    public void setErrorHandler(final Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
