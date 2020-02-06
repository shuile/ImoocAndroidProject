package com.imooc.method;

public class ExchangeDemo {
    public static void main(String[] args) {
        int m = 4, n =5;
        ExchangeDemo ed = new ExchangeDemo();
        System.out.println("交换前:m=" + m + ",n=" + n);
        ed.swap(m, n);
        System.out.println("交换后:m=" + m + ",n=" + n);
    }

    //交换方法
    public void swap(int a, int b) {
        int temp;
        System.out.println("交换前：a=" + a + ",b=" + b);
        temp = a;
        a = b;
        b = temp;
        System.out.println("交换后：a=" + a + ",b=" + b);
    }
}
