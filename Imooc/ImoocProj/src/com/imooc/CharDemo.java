package com.imooc;

public class CharDemo {
    public static void main(String[] args) {
        //定义一个变量存放字符'a'
        char a = 'a';
        System.out.println("a=" + a);
        char ch = 65;
        System.out.println("ch=" + ch);
        //如果字面值超出char类型所表示的数据范围,需要进行类型强制转换
        char ch1 = (char) 65536;
        System.out.println("ch1=" + ch1);
        char c = '\u005d';
        System.out.println("c=" + c);
    }
}
