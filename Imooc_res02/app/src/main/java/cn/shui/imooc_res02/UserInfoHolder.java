package cn.shui.imooc_res02;

import android.text.TextUtils;

import cn.shui.imooc_res02.bean.User;
import cn.shui.imooc_res02.util.SPUtils;

/**
 * Created by shui on 2019-12-12
 */
public class UserInfoHolder {

    private static UserInfoHolder mInstance = new UserInfoHolder();
    private User mUser;
    private static final String KEY_USERNAME = "key_username";

    public static UserInfoHolder getInstance() {
        return mInstance;
    }

    public void setUser(User user) {
        mUser = user;
        if (user != null) {
            SPUtils.getInstance().put(KEY_USERNAME, user.getUsername());
        }
    }

    public User getUser() {
        User u = mUser;
        if (u == null) {
            String username = (String) SPUtils.getInstance().get(KEY_USERNAME, "");
            if (!TextUtils.isEmpty(username)) {
                u = new User();
                u.setUsername(username);
            }
        }
        mUser = u;
        return u;
    }
}
