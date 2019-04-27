package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReportMarksListAdapter extends ArrayAdapter<Student>{
    private Activity context;
    private List<Student>reportMarks;
    DatabaseReference databaseStudents;

    public ReportMarksListAdapter(Activity context,List<Student>marksList){

        super(context,R.layout.report_student_list,marksList);
        this.context=context;
        this.reportMarks=marksList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View stMarksViewItem = inflater.inflate(R.layout.report_student_list, null, true);
        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");


        TextView stId = stMarksViewItem.findViewById(R.id.tvReportStId);
        TextView stMarks = stMarksViewItem.findViewById(R.id.tvReportStMarks);

        final Student studentMarksList = reportMarks.get(position);
        stId.setText(studentMarksList.getStId());
        stMarks.setText(String.valueOf(studentMarksList.getProvidedMark()));
        return stMarksViewItem;
    }

    }
