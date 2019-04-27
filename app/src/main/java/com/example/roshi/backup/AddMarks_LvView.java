package com.example.roshi.backup;
import java.io.Serializable;
public class AddMarks_LvView implements Serializable {

    private String stId;
    public Integer stMarks;
    public String markType;
    public String date;
    private String key;


    public void setDate(String date) {
        this.date = date;
    }

    public AddMarks_LvView(String stId, Integer stMarks, String markType, String date) {
        this.stId = stId;
        this.stMarks = stMarks;
        this.markType = markType;
        this.date = date;
    }


    public AddMarks_LvView() {
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public void setStMarks(int stMarks) {
        this.stMarks = stMarks;
    }

    public String getStId() {
        return null;
    }

    public Integer getStMarks() {
        return stMarks;
    }

    public String getMarkType() {
        return markType;
    }

    public String getDate() {
        return date;
    }


}
