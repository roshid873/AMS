package com.example.roshi.backup;

import java.io.Serializable;

public class Course implements Serializable{

    String courseName;

    public Course(){

    }

    public Course(String courseName) {
        this.courseName = courseName;

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


}
