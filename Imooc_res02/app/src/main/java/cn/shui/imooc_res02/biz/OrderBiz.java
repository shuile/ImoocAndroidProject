package cn.shui.imooc_res02.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.Map;

import cn.shui.imooc_res02.bean.Order;
import cn.shui.imooc_res02.bean.Product;
import cn.shui.imooc_res02.config.Config;
import cn.shui.imooc_res02.net.CommonCallback;

/**
 * Created by shui on 2019-12-13
 */
public class OrderBiz {

    public void listByPage(int currentPage, CommonCallback<List<Order>> commonCallback) {
        OkHttpUtils.post()
                .url(Config.baseURL + "order_find")
                .tag(this)
                .addParams("currentPage", currentPage + "")
                .build()
                .execute(commonCallback);
    }

    public void add(Order order, CommonCallback<String> commonCallback) {

        StringBuilder sb = new StringBuilder();
        Map<Product, Integer> productMap = order.productMap;
        for (Product p : productMap.keySet()) {
            sb.append(p.getId() + "_" + productMap.get(p));
            sb.append("|");
        }
        sb.deleteCharAt(sb.length() - 1);

        OkHttpUtils.post()
                .url(Config.baseURL + "order_add")
                .addParams("res_id", order.getRestaurant().getId() + "")
                .addParams("product_str", sb.toString())
                .addParams("count", order.getCount() + "")
                .addParams("price", order.getPrice() + "")
                .tag(this)
                .build()
                .execute(commonCallback);
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
