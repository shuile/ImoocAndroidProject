package cn.shui.imooc_res02;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import cn.shui.imooc_res02.util.SPUtils;
import cn.shui.imooc_res02.util.T;
import okhttp3.OkHttpClient;

/**
 * Created by shui on 2019-12-12
 */
public class ResApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        T.init(this);
        SPUtils.init(this, "sp_user.pref");

        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                // 其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
