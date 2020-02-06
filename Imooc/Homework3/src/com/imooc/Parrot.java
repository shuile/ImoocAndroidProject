package com.imooc;

public class Parrot extends Animal implements IACT {

    public String type;

    public Parrot() {
    }

    public Parrot(String name, int age, String type) {
        super(name, age);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void skill() {
        System.out.println("技能：擅长模仿");
    }

    @Override
    public void act() {
        System.out.println("表演者：" + getName());
        System.out.println("年龄：" + getAge() + "岁");
        System.out.println("品种：" + getType());
        skill();
        love();
        System.out.println();
    }

    @Override
    public void love() {
        super.love();
        System.out.println("爱好：喜欢吃坚果和松子");
    }
}
