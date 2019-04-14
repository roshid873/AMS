package com.example.roshi.backup;

import java.io.Serializable;

public class Student_List implements Serializable {

    private String stName,stId;
    private int providedMark;

    public int getProvidedMark() {
        return providedMark;
    }

    public void setProvidedMark(int providedMark) {
        this.providedMark = providedMark;
    }

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
