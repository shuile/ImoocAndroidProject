package cn.shui.ormlitedemo.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_student")
public class Student {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name", dataType = DataType.STRING, canBeNull = false)
    private String name;
    @DatabaseField
    private String age;
    @DatabaseField
    private String phone;
    @DatabaseField(columnName = "school_id", foreign = true, foreignAutoRefresh = true)
    private School school;

    public Student() {
    }

    public Student(String name, String age, String phone, School school) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
