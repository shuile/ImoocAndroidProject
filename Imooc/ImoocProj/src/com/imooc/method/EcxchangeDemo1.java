package com.imooc.method;

public class EcxchangeDemo1 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("方法调用前n的值:" + n);
    }

    public void add(int n) {
        n++;
        System.out.println("方法中n=" + n);
    }
}
