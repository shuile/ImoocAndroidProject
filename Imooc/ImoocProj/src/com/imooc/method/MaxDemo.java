package com.imooc.method;

public class MaxDemo {
    public static void main(String[] args) {
        MaxDemo maxDemo = new MaxDemo();
        int a = 5, b = 3;
        maxDemo.max(a, b);
    }

    //求最大值的方法
    public void max(float a, float b) {
        float max;
        if (a > b) {
            max = a;
        } else {
            max = b;
        }
        System.out.println("两个数" + a + "和" + b + "的最大值为：" + max);
    }

    public void max(double a, double b) {

    }
}
