package com.example.roshi.backup;
import java.io.Serializable;
public class AddMarks_LvView implements Serializable {

    private String stId,stMarks,markType,date;


    public AddMarks_LvView(String stId, String stMarks, String markType, String date) {
        this.stId = stId;
        this.stMarks = stMarks;
        this.markType = markType;
        this.date = date;
    }

    public AddMarks_LvView() {
    }


    public String getStId() {
        return null;
    }

    public String getStMarks() {
        return stMarks;
    }

    public String getMarkType() {
        return markType;
    }

    public String getDate() {
        return date;
    }

}
