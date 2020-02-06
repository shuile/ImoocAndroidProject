package com.imooc;

public class Monkey extends Animal implements IACT {

    public String type;

    public Monkey() {
    }

    public Monkey(String name, int age, String type) {
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
        System.out.println("技能：骑独轮车过独木桥");
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
        System.out.println("爱好：喜欢模仿人的动作表情");
    }
}
