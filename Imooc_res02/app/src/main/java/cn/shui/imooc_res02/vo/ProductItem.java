package cn.shui.imooc_res02.vo;

import cn.shui.imooc_res02.bean.Product;

/**
 * Created by shui on 2019-12-15
 */
public class ProductItem extends Product {

    public int count;

    public ProductItem(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.label = product.getLabel();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.icon = product.getIcon();
        this.restaurant = product.getRestaurant();
    }
}
