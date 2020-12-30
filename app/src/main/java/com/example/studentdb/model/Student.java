package com.example.studentdb.model;

import java.io.Serializable;

public class Student implements Serializable {

    int id;
    String name;
    int age;
    String course;
    String mobile;

    public Student(int id, String name, int age, String course, String mobile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.mobile = mobile;
    }

    public Student(String name, int age, String course, String mobile) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.mobile = mobile;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
