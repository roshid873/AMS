package com.example.roshi.backup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OptionDialogeActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button btnAddStudent,btnAddMrks,btnChangeMarks,btnReport,btnDelate;
    TextView courseView;
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
        /*course name add korte hobe as child of reference otherwise remove korte parba na
        ekon j obostai ache delete kora jabe na shakil k ami kore diyechilam . or code deke kaj kore nao . ami ajk onek busy tomake r help korte parchina
        **/
        studentList = (Student) getIntent().getSerializableExtra("stId");
        courseView=findViewById(R.id.tvOptionCourseView);
        btnAddStudent = findViewById(R.id.btAddStudent);
        btnAddMrks = findViewById(R.id.btAddMark);
        btnChangeMarks = findViewById(R.id.btChangeMark);
        btnReport = findViewById(R.id.btReport);
        btnDelate = findViewById(R.id.btDelate);
        databaseReference= FirebaseDatabase.getInstance().getReference("Courses");

        courseList = new ArrayList<>();
        courseView.setText(course.courseName);
        btnDelate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteCourses(course.getCourseId());
                Toast.makeText(OptionDialogeActivity.this,"Course Delete Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OptionDialogeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
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

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionDialogeActivity.this,ReportActivity.class);
                intent.putExtra("course",course);
                startActivity(intent);

            }
        });

    }

    private void deleteCourses(String courseId) {

        DatabaseReference drCourse = FirebaseDatabase.getInstance().getReference("Courses").child(courseId);
        DatabaseReference drStudent = FirebaseDatabase.getInstance().getReference("Students").child(course.courseName);
        DatabaseReference drMarks = FirebaseDatabase.getInstance().getReference("Marks").child(course.courseName);

        drCourse.removeValue();
        drStudent.removeValue();
        drMarks.removeValue();

    }
}
