package com.imooc.flow1;

import java.util.Scanner;

public class GuessDemo {
    public static void main(String[] args) {
        //设置要猜的数
        int number = (int) (Math.random() * 10 + 1);//使用随机数生成1到10之间的整数
        System.out.println("number=" + number);
        int guess;
        System.out.println("猜一个介于1到10之间的数");
        do {
            System.out.println("请输入您猜测的数：");
            Scanner sc = new Scanner(System.in);
            guess = sc.nextInt();
            if (guess > number) {
                System.out.println("太大！");
            } else if (guess < number) {
                System.out.println("太小！");
            }
        } while (number != guess);
        System.out.println("您猜中了！答案为" + number);
    }
}
