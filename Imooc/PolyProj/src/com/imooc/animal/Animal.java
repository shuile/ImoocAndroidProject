package com.imooc.animal;

//抽象类
public abstract class Animal {
    //属性：昵称、年龄
    private String name;
    private int month;

    public Animal() {
    }

    public Animal(String name, int month) {
        this.name = name;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    //方法：吃东西
    //抽象方法：不允许包含方法体；子类中需要重写父类的抽象方法，否则，子类也是抽象类
    //static final private
    public abstract void eat();

    public static void say() {
        System.out.println("动物间打招呼");
    }
}
