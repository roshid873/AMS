package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Student_inflater extends ArrayAdapter<Student> {

    private Activity context;
    private List<Student>studentLists;


    public Student_inflater(Activity context, List<Student> student__inflater){

        super(context,R.layout.student_data, student__inflater);
        this.context = context;
        this.studentLists = student__inflater;


    }
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View stListViewItem = inflater.inflate(R.layout.student_data,null,true);


        TextView stId = stListViewItem.findViewById(R.id.stIdData);
        TextView stName = stListViewItem.findViewById(R.id.stNameData);

        Student studentList = studentLists.get(position);
        if (studentList.getStName() != null) {
            String stName1 = studentList.getStName();
            stName.setText(stName1);
        }

        stId.setText(studentList.getStId());
        stName.setText(studentList.getStName());

        return stListViewItem;
    }

}
