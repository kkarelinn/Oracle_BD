package com.bd.entities;

import java.util.Date;

public class Employee {

    private int ID;
    private String name;
    private String job;
    private int mgrID;
    private Date hireDate;
    private float salary;
    private float comm;
    private int grade;
    private int deptID;
    private Department department;

    public Employee() {
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(int ID,
                    String name,
                    String job,
                    int mgrID,
                    Date hireDate,
                    float salary,
                    float comm,
                    int grade,
                    int deptID,
                    Department department) {
        this.ID = ID;
        this.name = name;
        this.job = job;
        this.mgrID = mgrID;
        this.hireDate = hireDate;
        this.salary = salary;
        this.comm = comm;
        this.grade = grade;
        this.deptID = deptID;
        this.department = department;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgrID() {
        return mgrID;
    }

    public void setMgrID(int mgrID) {
        this.mgrID = mgrID;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", mgrID=" + mgrID +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", comm=" + comm +
                ", grade=" + grade +
                ", deptID=" + deptID +
                ", dep Name=" + department.getDeptName() +
                ", dep Loc=" + department.getLocation()+
                '}';
    }
}
