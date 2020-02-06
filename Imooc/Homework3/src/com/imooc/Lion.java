package com.imooc;

public class Lion extends Animal implements IACT {

    public String color;
    public String sex;

    public Lion() {
    }

    public Lion(String name, int age, String color, String sex) {
        super(name, age);
        this.color = color;
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void skill() {
        System.out.println("技能：擅长钻火圈");
    }

    @Override
    public void act() {
        System.out.println("表演者：" + getName());
        System.out.println("年龄：" + getAge() + "岁");
        System.out.println("性别：" + getSex());
        System.out.println("毛色：" + getColor());
        skill();
        love();
        System.out.println();
    }

    @Override
    public void love() {
        super.love();
        System.out.println("爱好：细化吃各种肉类");
    }
}
