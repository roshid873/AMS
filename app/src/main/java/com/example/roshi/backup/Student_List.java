package com.example.roshi.backup;

import java.io.Serializable;

public class Student_List implements Serializable {


    private String stName,stId;


    public Student_List(){

    }

    public Student_List(String stName, String stId) {
        this.stName = stName;
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public String getStId() {
        return stId;
    }


}
