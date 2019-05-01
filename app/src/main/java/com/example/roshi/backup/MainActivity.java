 package com.example.roshi.backup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {
     ImageView ivLogo;
     EditText etCourseName;
     Button btOk;
     ListView lvCourseList;
     private DatabaseReference databaseCourse;
     List<Course> courseList;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseCourse = FirebaseDatabase.getInstance().getReference("Courses");
        etCourseName = findViewById(R.id.coursename);
        btOk = findViewById(R.id.button);
        lvCourseList = findViewById(R.id.listview1);
        ivLogo = findViewById(R.id.imageView);
        courseList = new ArrayList<>();

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            addCourse();


            }
        });
         databaseCourse.addChildEventListener(new ChildEventListener() {



             @Override
             public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                 Course course = dataSnapshot.getValue(Course.class);
                 courseList.add(course);
                 CourseList adapter = new CourseList(MainActivity.this, courseList);
                 lvCourseList.setAdapter(adapter);
             }


             @Override
             public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

             }

             @Override
             public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

         lvCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(MainActivity.this,OptionDialogeActivity.class);
                 intent.putExtra("course", courseList.get(position));
                 startActivity(intent);
             }
         });


    }
        private void addCourse(){

            String courseName = etCourseName.getText().toString().trim();
            if(!TextUtils.isEmpty(courseName)){

                String id = databaseCourse.push().getKey();
                Course course = new Course(courseName,id);
                databaseCourse.child(id).setValue(course);
                Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(MainActivity.this, "Please enter a Course !", Toast.LENGTH_LONG).show();
            }
            etCourseName.setText("");

        }




    }

