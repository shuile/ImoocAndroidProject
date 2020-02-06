package cn.imooc.imoochomework_android03;

public class Person {

    private String name;
    private String sex;
    private int age;
    private String borrowTime;
    private Book book;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Person [per_name=" + name
                + ", sex=" + sex
                + ", age=" + age
                + ", time=" + borrowTime
                + "]";
    }
}
