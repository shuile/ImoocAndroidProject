package com.imooc.flow1;

public class startDemo1 {
    public static void main(String[] args) {
        int m = 1;//外重循环的循环变量
        int n = 1;//内重循环的循环变量
        System.out.println("输出4行4列的星号");
        while (m <= 4) {
            //内重循环控制每行输出几个星号
            n = 1;
            while (n <= m) {
                System.out.print("*");
                n++;
            }
            System.out.println();
            m++;
        }
    }
}
