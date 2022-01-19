package com.bd.entities;

public class SalGrade {

    private int grade;
    private double minSal;
    private double hiSal;

    public SalGrade() {
    }

    public SalGrade(int grade, double minSal, double hiSal) {
        this.grade = grade;
        this.minSal = minSal;
        this.hiSal = hiSal;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getMinSal() {
        return minSal;
    }

    public void setMinSal(double minSal) {
        this.minSal = minSal;
    }

    public double getHiSal() {
        return hiSal;
    }

    public void setHiSal(double hiSal) {
        this.hiSal = hiSal;
    }
}
