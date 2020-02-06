package cn.shui.imooc_res02.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import cn.shui.imooc_res02.bean.Product;
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.net.CommonCallback;

/**
 * Created by shui on 2019-12-15
 */
public class ProductBiz {

    public void listByPage(int currentPage, CommonCallback<List<Product>> commonCallback) {
        OkHttpUtils.post()
                .url(Config.baseURL + "product_find")
                .addParams("currentPage", currentPage + "")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    public void onDestroy() {
         OkHttpUtils.getInstance().cancelTag(this);
    }
}
