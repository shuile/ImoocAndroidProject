package com.imooc.people;

public class Person {
    public static int age;

    public Object getHeart() {
        //方法内部类
        /*
         *1、定义在方法内，作用范围也在方法内
         * 2、和方法内部成员使用规则一样，class前面不可以添加public、private、protected、static
         * 3、类中不能包含静态成员
         * 4、类中可以包含final、abstract修饰
         */
        class Heart{
            public int age = 13;
            int temp = 22;
            public void say () {
                System.out.println("hello");
            }
            public void eat () {

            }
            public String beat () {
                new Person().eat();
                return Person.age + "岁的心脏在跳动";
            }
        }
        return new Heart().beat();
//        new Heart().temp = 12;
//        return new Heart();
    }

    public void eat() {
        System.out.println("人会吃东西");
    }

    //成员内部类
    /**
     * 1、内部类在外部使用时，我发直接实例化，需要借由外部类信息才能完成实例化
     * 2、内部类的访问修饰符，可以任意，但是访问范围会受到影响
     * 3、内部类可以直接访问外部类的成员；如果出现同名属性，有线访问内部类中定义的
     * 4、可以使用外部类.this.成员的方式，访问外部类中同名的信息
     * 5、外部类访问内部类信息，需要通过内部类实例，无法直接访问
     * 6、内部类编译后.class文件命名：外部类$内部类.class
     * 7、内部类中是否可以包含于外部类相同方法签名的方法？
     */
//    class Heart {
//        int age = 13;
//        int temp = 22;
//        public String beat() {
//            int age = 13;
//            eat();
//            return age + "心脏在跳动";
//        }

//    public static class Heart(){
//        public static int age=13;
//        int temp=22;
//        public static void say(){
//            System.out.println("hello");
//        }
//        public void eat(){
//
//        }
//        public String beat(){
//            new Person().eat();
//            return Person.age+"岁的心脏在跳动";
//        }
//    }
}
