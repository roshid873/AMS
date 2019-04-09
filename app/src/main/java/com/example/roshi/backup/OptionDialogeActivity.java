package com.example.roshi.backup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class OptionDialogeActivity extends AppCompatActivity {

    Button btnAddStudent,btnAddMrks,btnChangeMarks,btnReport,btnDelate;

    Course course;
    List<Course> courseList;
    Student_List studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_dialoge);
        course = new Course();
        studentList = new Student_List();
        course = (Course) getIntent().getSerializableExtra("course");
        studentList = (Student_List) getIntent().getSerializableExtra("stId");


        btnAddStudent = findViewById(R.id.btAddStudent);
        btnAddMrks = findViewById(R.id.btAddMark);
        btnChangeMarks = findViewById(R.id.btChangeMark);
        btnReport = findViewById(R.id.btReport);
        btnDelate = findViewById(R.id.btDelate);

        courseList = new ArrayList<>();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionDialogeActivity.this,AddStudentActivity.class);
                intent.putExtra("course",course);
                startActivity(intent);
            }
        });

        btnAddMrks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionDialogeActivity.this,AddMarksActivity.class);
                intent.putExtra("course",course);
                startActivity(intent);

            }
        });

    }
}
