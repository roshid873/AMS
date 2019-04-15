package com.example.roshi.backup;

import android.content.Intent;
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
    Student studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_dialoge);
        course = new Course();
        studentList = new Student();
        course = (Course) getIntent().getSerializableExtra("course");
        studentList = (Student) getIntent().getSerializableExtra("stId");


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

        btnChangeMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionDialogeActivity.this,ChangeMarksActivity.class);
                intent.putExtra("course",course);
                startActivity(intent);

            }
        });

    }
}
