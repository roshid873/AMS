package com.example.roshi.backup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MenualActivity extends AppCompatActivity {

    EditText manualName,manualId;
    Button btnAdd;
    TextView courseView;

    Course course;
       private DatabaseReference databaseStudents;

        List<Student> student__inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menual);
        databaseStudents = FirebaseDatabase.getInstance().getReference("Students");
        courseView=findViewById(R.id.tvMenualCourseView);
        manualName=findViewById(R.id.etManualName);
        manualId=findViewById(R.id.etManualId);
        student__inflater = new ArrayList<>();
        btnAdd=findViewById(R.id.btManualAdd);
        course = (Course) getIntent().getSerializableExtra("course");
        courseView.setText(course.courseName);

       btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String stName = manualName.getText().toString().trim();
            String stId = manualId.getText().toString().trim();
            if(!TextUtils.isEmpty(stName)) {
                if (!TextUtils.isEmpty(stId)){

                    databaseStudents.child(course.courseName).push().setValue(new Student(stName,stId)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(MenualActivity.this, "Student Add successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MenualActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }

                    });
                    manualName.setText("");
                    manualId.setText("");
            }

                else {

                    Toast.makeText(MenualActivity.this,"Please Enter Student Name", Toast.LENGTH_SHORT).show();

                }

            }

            else if(!TextUtils.isEmpty(stId)) {
                if(!TextUtils.isEmpty(stName)) {
                    databaseStudents.child(course.courseName).push().setValue(new Student(stName, stId)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(MenualActivity.this, "Student Add successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MenualActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }

                    });
                    manualName.setText("");
                    manualId.setText("");
                }

                else {

                    Toast.makeText(MenualActivity.this,"Please Enter Student ID", Toast.LENGTH_SHORT).show();

                }


                }




            }
        });
    }
}
