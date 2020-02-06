package cn.shui.imooc_res02.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import cn.shui.imooc_res02.bean.User;
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.net.CommonCallback;

/**
 * Created by shui on 2019-12-10
 */
public class UserBiz {

    public void login(String username, String password, CommonCallback<User> commonCallback) {
        OkHttpUtils
                .post()
                .url(Config.baseURL + "user_login")
                .tag(this)
                .addParams("username", username)
                .addParams("password", password)
                .build()
                .execute(commonCallback);
    }

    public void register(String username, String password, CommonCallback<User> commonCallback) {
        OkHttpUtils
                .post()
                .url(Config.baseURL + "user_register")
                .tag(this)
                .addParams("username", username)
                .addParams("password", password)
                .build()
                .execute(commonCallback);
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
