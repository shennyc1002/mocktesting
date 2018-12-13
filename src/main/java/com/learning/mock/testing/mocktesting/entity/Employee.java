package com.learning.mock.testing.mocktesting.entity;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @Column(name="EMPLOYEE_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int employeeId;

    @Column(name="EMPLOYEE_FIRST_NAME")
    private String employeeFirstName;

    @Column(name="EMPLOYEE_LAST_NAME")
    private String employeeLastName;

    @Column(name="EMPLOYEE_AGE")
    private int employeeAge;

    @Column(name="EMPLOYEE_SALARY")
    private int employeeSalary;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
