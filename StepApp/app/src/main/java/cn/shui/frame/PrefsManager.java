package cn.shui.frame;

import android.content.Context;

/**
 * Created by shui on 2020/5/5
 */
public class PrefsManager {

    private final Context mContext;
    private static final String PREFERENCE_NAME = "imooc_step";

    public PrefsManager(Context mContext) {
        this.mContext = mContext;
    }

    public void clear() {
        mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
                .clear().commit();
    }

    public boolean contains() {
        return mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .contains(PrefsManager.PREFERENCE_NAME);
    }

    public boolean getBoolean(final String key) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getBoolean(key, false);
    }

    public Boolean getBooleanDefaultTrue(final String key) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getBoolean(key, true);
    }

    public boolean putBoolean(final String key, final boolean value) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean(key, value).commit();
    }

    public int getInt(final String key) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getInt(key, -1);
    }

    public boolean putInt(final String key, final int value) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putInt(key, value).commit();
    }

    public String getString(final String key) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(key, null);
    }

    public boolean putString(final String key, final String value) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
    }

    public float getFloat(final String key) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getFloat(key, -1);
    }

    public boolean putFloat(final String key, final float value) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putFloat(key, value).commit();
    }

    public long getLong(final String key) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getLong(key, -1);
    }

    public boolean putLong(final String key, final long value) {
        return this.mContext.getSharedPreferences(PrefsManager.PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putLong(key, value).commit();
    }
}
