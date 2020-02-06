package com.imooc;

public class Clown implements IACT {

    public String name;
    public int years;

    public Clown() {
    }

    public Clown(String name, int years) {
        this.name = name;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    /**
     *着装特点
     */
    public void dress() {
        System.out.println("着装：身穿五彩服装，头上戴着彩色的帽子，脸上画着夸张的彩妆");
    }

    @Override
    public void skill() {
        System.out.println("技能：脚踩高跷，进行杂技魔术表演");
    }

    @Override
    public void act() {
        System.out.println("表演者：" + getName());
        System.out.println("艺龄：" + getYears() + "年");
        dress();
        skill();
        System.out.println();
    }
}
