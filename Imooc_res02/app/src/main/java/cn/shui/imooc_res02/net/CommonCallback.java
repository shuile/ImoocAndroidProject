package cn.shui.imooc_res02.net;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.shui.imooc_res02.util.GsonUtil;
import okhttp3.Call;

/**
 * Created by shui on 2019-12-12
 */
public abstract class CommonCallback<T> extends StringCallback {

    private static final String TAG = "CommonCallback";

    Type mType;

    public CommonCallback() {
        Class<? extends CommonCallback> clazz = getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();

        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Miis Type params");
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        mType = parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Log.e(TAG, "onError: " + e.getMessage());
        onError(e);
    }

    @Override
    public void onResponse(String response, int id) {
        Log.d(TAG, "onResponse: " + response);
        try {
            JSONObject resp = new JSONObject(response);
            int resultCode = resp.getInt("resultCode");

            if (resultCode == 1) { //成功
                String data = resp.getString("data");
                onSuccess((T) GsonUtil.getGson().fromJson(data, mType));
            } else {
                onError(new RuntimeException(resp.getString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public abstract void onError(Exception e);

    public abstract void onSuccess(T response);
}
