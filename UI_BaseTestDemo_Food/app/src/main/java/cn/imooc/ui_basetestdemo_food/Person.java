package cn.imooc.ui_basetestdemo_food;

public class Person {

    private String name;
    private String sex;
    Food food;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Person [name=" + name
                + ", sex=" + sex
                + ", food=" + food
                + "]";
    }
}
