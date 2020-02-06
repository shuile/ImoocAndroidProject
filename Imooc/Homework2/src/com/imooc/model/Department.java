package com.imooc.model;

public class Department {
    private String departmentNo;
    private String departmentName;
    private Employee[] departmentEmployee;
    private int employeeNum;

    public Department() {
    }

    public Department(String departmentNo, String departmentNmae) {
        this.departmentNo = departmentNo;
        this.departmentName = departmentNmae;
    }

    public Department(String departmentNo, String departmentName, Employee[] departmentEmployee, int employeeNum) {
        this.setDepartmentNo(departmentNo);
        this.setDepartmentName(departmentName);
        this.setDepartmentEmployee(departmentEmployee);
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentNmae) {
        this.departmentName = departmentNmae;
    }

    public Employee[] getDepartmentEmployee() {
        if (this.departmentEmployee == null) {
            this.departmentEmployee = new Employee[1000];
        }
        return departmentEmployee;
    }

    public void setDepartmentEmployee(Employee[] departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    public void addEmployee(Employee employee) {
        for (int i = 0 ; i < this.getDepartmentEmployee().length ; i++) {
            if (this.getDepartmentEmployee()[i] == null) {
                employee.setEmployeeDepartment(this);
                this.getDepartmentEmployee()[i] = employee;
                this.employeeNum = i + 1;
                return;
            }
        }
    }
}
