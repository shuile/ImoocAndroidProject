package com.imooc.animal;

/**
 * final class:该类没有子类 public final class   final public class
 * final 方法：该方法不允许被子类重写，但是可以正常被子类继承使用
 * final 方法内局部变量：主要在具体被使用之前进行赋值即可，一旦赋值不允许被修改
 *       类中成员属性：赋值过程：1、定义直接初始化  2、构造方法  3、构造代码块
 */
public class Animal {
    /*
     * private:只允许在本类中进行访问
     * public:允许在任意位置访问
     * protected:允许在当前类、同包子类/非子类、跨包子类调用、跨包非子类不允许
     * 默认：允许在当前类、同包子类/非子类调用；跨包子类/非子类不允许调用
     */

    private String name = "妮妮";//名称
    private int month = 2;//月份
    private String species = "动物";//品种
    public int temp = 15;
    private static int st1 = 22;
    public static int st2 = 23;
    public final int anInt = 15;

    static {
        System.out.println("我是父类的静态代码块");
    }

    {
        System.out.println("我是父类的构造代码块");
    }

    public Animal(String name, int month){
        this.name = name;
        this.month = month;
        System.out.println("我是父类的带参构造方法");
    }

    //父类的构造不允许被继承、不允许被重写，但是会影响子类对象的实例化过程
    public Animal() {
        month = 2;
        System.out.println("我是父类的无参构造方法");
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    //吃东西
    public final void eat() {
        System.out.println(this.getName() + "在吃东西");
    }

    public void eat(String name) {
//        final int temp = 10;//方法内局部变量
        System.out.println(name + "在吃东西");
        System.out.println(temp);

        final Animal animal = new Animal("凡凡", 1);
//        animal = new Animal();
    }


    @Override
    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
        Animal temp = (Animal) obj;
        if (this.getName().equals(temp.getName()) && this.getMonth() == temp.getMonth()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Animal animal) {
//         if (obj == null) {
//             return false;
//         }
         if (this.getName().equals(animal.getName()) && this.getMonth() == animal.getMonth()) {
             return true;
         } else {
             return false;
         }
    }

    @Override
    public String toString() {
        return "昵称：" + this.getName() + "；年龄：" + this.getMonth();
    }

    public Animal create() {
        return new Animal();
    }
}
