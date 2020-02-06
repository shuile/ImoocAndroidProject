package com.imooc.animal;

/**
 * 宠物猫类
 * @author chenyiting
 */
public class Cat {
    //属性：昵称、年龄、体重、品种
    //修改属性的可见性---private 限定只能在当前类内访问
    private String name;//昵称 String默认值null
    private int month;//年龄 int默认值0
    private double weight;//体重 double默认值0.0
    private String species;//品种 String默认值null

    //static:静态 静态成员、类成员
    //
    public static int price;//售价

    public Cat(String name) {
        System.out.println("我是无参构造方法");
    }

    public Cat(String name, int month, double weight, String species) {
        this.name = name;
        this.month = month;
        this.weight = weight;
        this.species = species;
    }

    public Cat() {
        System.out.println("我是宠物猫");
    }

    public Cat(int month) {
        this.setMonth(month);
    }

    //创建get/set方法
    //在get/set方法中添加对属性的限定
    public String getName() {
        return "我是一只名叫" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month <= 0) {
            System.out.println("输入信息错误，宠物猫的年龄必须大于0");
        } else {
            this.month = month;
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    //方法：跑动、吃东西
    //跑动的方法
    public void run() {
        System.out.println("小猫快跑");
    }

    public void run(String name) {
        System.out.println(name + "小猫快跑");
    }

    //吃东西的方法
    public void eat() {
//        int age;
        System.out.println("小猫吃鱼");
//        System.out.println(age);
    }
}
