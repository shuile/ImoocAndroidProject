package com.imooc.method;

public class Rectangle {
    public static void main(String[] args) {
        Rectangle rc = new Rectangle();
        System.out.println(rc.area());
    }

    //求长方形面积的方法
    public int area() {
        int length = 10;
        int width = 5;
        int getArea = length * width;
        return getArea;//返回语句
    }
}
