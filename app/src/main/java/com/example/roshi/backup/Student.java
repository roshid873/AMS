package com.example.roshi.backup;

import java.io.Serializable;

public class Student implements Serializable {

    private String stName,stId;
    private int providedMark;

    public int getProvidedMark() {
        return providedMark;
    }

    public void setProvidedMark(int providedMark) {
        this.providedMark = providedMark;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public Student(){

    }

    public Student(String stName, String stId) {
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
