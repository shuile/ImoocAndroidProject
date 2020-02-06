package com.imooc.animal;

public class Cat extends Animal {
    //属性：体重
    private double weight;

    public Cat() {
    }

    public Cat(String name, int month, double weight) {
        super(name, month);
//        this.setMonth(month);
        this.weight = weight;
    }

    //方法：跑动
    public void run() {
        System.out.println("小猫快了的奔跑");
    }

    //方法：吃东西
    @Override
    public void eat() {
        System.out.println("猫吃鱼~~~");
    }

    public static void say() {
        System.out.println("小猫碰胡须");
    }

    public void playBall() {
        System.out.println("小猫喜欢玩线球");
    }
}
