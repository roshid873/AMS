package com.example.roshi.backup;

import java.io.Serializable;

public class Course implements Serializable{

    String courseName;
    String courseId;

    public Course(){

    }

    public Course(String courseName,String courseId) {
        this.courseName = courseName;
        this.courseId=courseId;

    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


}
