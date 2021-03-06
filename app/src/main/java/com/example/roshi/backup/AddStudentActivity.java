package com.example.roshi.backup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddStudentActivity extends AppCompatActivity {

    Button btnManual,btnFile;
    TextView tvStName,tvStId;
    ListView lvStList;
    DatabaseReference databaseStudents;
    List<Student_List> Student_List_inflater;

    Course course;
    List<com.example.roshi.backup.Course> courseList;
    com.example.roshi.backup.Student_List studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

       btnManual = findViewById(R.id.ad_st_bt1);
        btnFile=findViewById(R.id.ad_st_bt2);
        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");
        tvStName=findViewById(R.id.ad_st_tv1);
        tvStId=findViewById(R.id.ad_st_tv2);

        //pass data
        course = new com.example.roshi.backup.Course();
        studentList = new com.example.roshi.backup.Student_List();
        course = (com.example.roshi.backup.Course) getIntent().getSerializableExtra("course");


        Student_List_inflater = new ArrayList<>();
        courseList = new ArrayList<>();

        lvStList=findViewById(R.id.ad_st_lv);


            btnManual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AddStudentActivity.this, com.example.roshi.backup.MenualActivity.class);
                    //pass data
                    intent.putExtra("course",course);
                    startActivity(intent);
                }
            });

        databaseStudents.child(course.getCourseName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student_List_inflater.clear();

                for(DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                    com.example.roshi.backup.Student_List student_list = studentSnapshot.getValue(com.example.roshi.backup.Student_List.class);
                    Student_List_inflater.add(student_list);

                    com.example.roshi.backup.Student_List_inflater adapter = new com.example.roshi.backup.Student_List_inflater(AddStudentActivity.this, Student_List_inflater);
                    lvStList.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddStudentActivity.this,AddFileActivity.class);
                intent.putExtra("course",course);
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file


        }
    }
}
