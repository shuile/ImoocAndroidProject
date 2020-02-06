package cn.shui.imooc_res02.util;

import com.google.gson.Gson;

/**
 * Created by shui on 2019-12-12
 */
public class GsonUtil {

    public static Gson mGson = new Gson();

    public static Gson getGson() {
        return mGson;
    }

}
