package com.example.roshi.backup;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.List;

import static org.junit.Assert.*;

public class AddMarks_LvView_inflaterTest extends ArrayAdapter<AddMarks_LvView>{

    private Activity context;
    private List<AddMarks_LvView> marksLists;

    public AddMarks_LvView_inflaterTest(Activity context, List<AddMarks_LvView> AddMarks_LvView_inflaterTest){

        super(context,R.layout.add_marks_list.AddMarks_LvView_inflaterTest);
        this.context = context;
        this.studentLists = Student_inflater;


    }


}