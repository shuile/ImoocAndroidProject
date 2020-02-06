package com.imooc.serial;

import java.io.Serializable;

public class Goods implements Serializable {

    private String goodsId;
    private String goodsName;
    private double goodsPrice;

    public Goods() {
    }

    public Goods(String goodsId, String goodsName, double goodsPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Override
    public String toString() {
        return "商品信息[编号：" + goodsId + "，名称：" + goodsName + "，价格：" + goodsPrice + "]";
    }
}
