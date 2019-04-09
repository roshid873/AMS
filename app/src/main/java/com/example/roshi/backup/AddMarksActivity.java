package com.example.roshi.backup;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMarksActivity extends AppCompatActivity {

    private static final String TAG = "AddMarksActivity";
    private DatePickerDialog.OnDateSetListener tvDate;

    EditText marksType,marks;
    DatabaseReference databaseStudentsId;
    ListView lvMarksList;
    TextView date;
    DatabaseReference addMark;
    Course course;

    AddMarks_LvView addMarksLvView;
    FirebaseDatabase database;
    List<Student_List> studentInformasion;
    String dateSelect;
    Button btnAddMark;
    List<AddMarks_LvView> marksLvViews;

    List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        marksType = findViewById(R.id.etMarkType);
        lvMarksList = findViewById(R.id.ad_mrk_lv1);
        date = findViewById(R.id.tvDate);
        marks = findViewById(R.id.etmarks);
        btnAddMark = findViewById(R.id.addMarkBt);

        databaseStudentsId = FirebaseDatabase.getInstance().getReference("Students");
        addMark = FirebaseDatabase.getInstance().getReference("Marks");
        studentInformasion = new ArrayList<>();
        marksLvViews = new ArrayList<>();
        course = new Course();
        addMarksLvView = new AddMarks_LvView();
        course = (Course) getIntent().getSerializableExtra("course");
        courseList = new ArrayList<>();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddMarksActivity.this,
                        android.R.style.Theme_DeviceDefault,tvDate,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                dialog.show();
            }
        });

        tvDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Log.d(TAG,"onDateSet : date : "+i + "/" + (i1+1) +"/" + i2);
                dateSelect = (i1+1)+ "/" + i2 + "/" + i;
                date.setText(dateSelect);
            }
        };

     databaseStudentsId.child(course.getCourseName()).addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             studentInformasion.clear();
             for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                 Student_List studentList = studentSnapshot.getValue(Student_List.class);
                 studentInformasion.add(studentList);

                 AddMarks_LvView_inflater adapter = new AddMarks_LvView_inflater(AddMarksActivity.this, studentInformasion );
                 lvMarksList.setAdapter(adapter);

             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });

     btnAddMark.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            //String lists = lvMarksList.getAdapter().toString();
            //String markType = marksType.getText().toString().trim();
             //String stMarks = marks.getText().toString().trim();
             addMark.child(course.getCourseName()).child(addMarksLvView.getDate())
                     .child(addMarksLvView.getMarkType()).child(addMarksLvView.getStId())
                     .push().setValue(new AddMarks_LvView()).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {

                     if (task.isSuccessful()) {
                         Toast.makeText(AddMarksActivity.this, "Marks add successfully", Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(AddMarksActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                     }

                 }
             });

         }
     });

    }

}
