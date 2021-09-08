package com.wbhz.entity;

public class Student {
    private String stuName;
    private int stuAge;

    public Student(String stuName, int stuAge) {
        super();
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student [stuAge=" + stuAge + ", stuName=" + stuName + "]";
    }
}
