package com.imooc.model;

public class Employee {
    private String employeeName;
    private String employeeNo;
    private int employeeAge;
    private String employeeSex;
    private Department employeeDepartment;
    private Position employeePosition;

    public Employee() {
    }

    public Employee(String employeeName, String employeeNo, int employeeAge, String employeeSex,
                    Department employeeDepartment, Position employeePosition) {
        this.setEmployeeName(employeeName);
        this.setEmployeeNo(employeeNo);
        this.setEmployeeAge(employeeAge);
        this.setEmployeeSex(employeeSex);
        this.setEmployeeDepartment(employeeDepartment);
        this.setEmployeePosition(employeePosition);
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        if (employeeAge >= 18 && employeeAge <= 65) {
            this.employeeAge = employeeAge;
        } else {
            this.employeeAge = 18;
        }
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        if (employeeSex.equals("男") || employeeSex.equals("女")) {
            this.employeeSex = employeeSex;
        } else {
            this.employeeSex = "男";
        }
    }

    public Department getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(Department employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public Position getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(Position employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String introduction() {
        String str  = "姓名：" + this.getEmployeeName() + "\n工号：" + this.getEmployeeNo() + "\n性别："
                + this.getEmployeeSex() + "\n年龄：" + this.getEmployeeAge() + "\n职务："
                + this.getEmployeeDepartment().getDepartmentName() + this.getEmployeePosition().getPositionName();
        return str;
    }
}
