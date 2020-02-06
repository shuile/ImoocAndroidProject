package com.imooc.animal;
//单一职责原则
public class CatTest {
    public static void main(String[] args) {
        //对象实例化
        Cat one = new Cat("");
//        Cat one = new Cat("花花", 2, 1000, "英国短毛猫");
//        Cat two = new Cat();
//        Cat two = one;
//        Cat one;//声明对象
        //测试
//        one.setName("凡凡");
        one.setMonth(0);
//        one.weight = 10000;
//        one.species = "英国短毛猫";
//        two.name = "凡凡";
//        two.month = 1;
//        two.weight = 800;
//        two.species = "中华田园猫";
//        System.out.println("昵称：" + one.getName());
        if (one.getMonth() == 0) {
            return;
        }
        System.out.println("年龄：" + one.getMonth());
//        System.out.println("体重：" + one.weight);
//        System.out.println("品种：" + one.species);
        System.out.println("------------------------");
//        System.out.println("昵称：" + two.name);
//        System.out.println("年龄：" + two.month);
//        System.out.println("体重：" + two.weight);
//        System.out.println("品种：" + two.species);
    }
}
