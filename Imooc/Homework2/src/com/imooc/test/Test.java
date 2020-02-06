package com.imooc.test;

import com.imooc.model.Department;
import com.imooc.model.Employee;
import com.imooc.model.Position;

public class Test {
    public static void main(String[] args) {
        Department d1 = new Department("D1", "人事部");
        Department d2 = new Department("D2", "市场部");
        Position p1 = new Position("P1", "经理");
        Position p2 = new Position("P2", "职员");
        Position p3 = new Position("P3", "助理");
        Employee[] e = new Employee[6];
        e[0] = new Employee("张铭", "S001", 29, "男", d1, p1);
        e[1] = new Employee("李艾爱", "S002", 21,"女", d1, p3);
        e[2]  =new Employee("孙超", "S004", 29, "男", d1, p2);
        e[3] = new Employee("张美美", "S005", 26, "女", d2, p2);
        e[4] = new Employee("蓝迪", "S006", 37, "男", d2, p1);
        e[5] = new Employee("米莉", "S007", 24, "女", d2, p2);
        d1.addEmployee(e[0]);
        d1.addEmployee(e[1]);
        d1.addEmployee(e[2]);
        d2.addEmployee(e[3]);
        d2.addEmployee(e[4]);
        d2.addEmployee(e[5]);
        for (Employee ee : e) {
            System.out.println(ee.introduction());
            System.out.println("===========================");
        }
        System.out.println(d1.getDepartmentName() + "总共有" + d1.getEmployeeNum() + "名员工");
        System.out.println(d2.getDepartmentName() + "总共有" + d2.getEmployeeNum() + "名员工");
    }
}
