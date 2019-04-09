package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddMarks_LvView_inflater extends ArrayAdapter<Student_List> {


    private Activity context;
    private List<Student_List>marksLists;
    Student_List studentList;
    DatabaseReference databaseStudents;

    public AddMarks_LvView_inflater(Activity context, List<Student_List> marksLists){

       super(context, R.layout.add_marks_list, marksLists);
        this.context = context;
        this.marksLists = marksLists;


    }
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View stMarksViewItem = inflater.inflate(R.layout.add_marks_list,null,true);
        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");



        TextView stId = stMarksViewItem.findViewById(R.id.tvId);
        EditText stMarks= stMarksViewItem.findViewById(R.id.etmarks);

        Student_List studentMarksList = marksLists.get(position);


        stId.setText(studentMarksList.getStId());
        //stMarks.setText(studentMarksList.getStMarks());
        stMarks.getText();

        return stMarksViewItem;
    }

}
