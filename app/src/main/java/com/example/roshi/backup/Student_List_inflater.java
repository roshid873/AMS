package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Student_List_inflater extends ArrayAdapter<Student_List> {

    private Activity context;
    private List<Student_List>studentLists;


    public Student_List_inflater(Activity context, List<Student_List> Student_List_inflater){

        super(context,R.layout.student_data,Student_List_inflater);
        this.context = context;
        this.studentLists = Student_List_inflater;


    }
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View stListViewItem = inflater.inflate(R.layout.student_data,null,true);


        TextView stId = stListViewItem.findViewById(R.id.stIdData);
        TextView stName = stListViewItem.findViewById(R.id.stNameData);

        Student_List studentList = studentLists.get(position);
        if (studentList.getStName() != null) {
            String stName1 = studentList.getStName();
            stName.setText(stName1);
        }

        stId.setText(studentList.getStId());
        stName.setText(studentList.getStName());

        return stListViewItem;
    }

}
