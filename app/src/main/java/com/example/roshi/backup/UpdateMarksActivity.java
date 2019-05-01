package com.example.roshi.backup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UpdateMarksActivity extends AppCompatActivity {
    private static final String TAG ="UpdateMarksActivity";
    private DatePickerDialog.OnDateSetListener tvDate;
    String dateSelect;
    TextView updateDate,updateStudenId,updateMarks,courseView;
    EditText updateMarksType;
    ListView updateListView;
    Button upButton;
    Course course;
    AddMarks_LvView dates;
    DatabaseReference databaseReference;
    List<Student>marksListview;
    private UpdateAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_marks);
        courseView=findViewById(R.id.tvUpdateMarksCourseView);
        updateDate=findViewById(R.id.tvUpdateDate);
        updateStudenId=findViewById(R.id.tvUpdateStudenId);
        updateMarks=findViewById(R.id.tvUpdateMarks);
        updateMarksType=findViewById(R.id.etUpdateMarkType);
        updateListView=findViewById(R.id.updateListView);
        upButton=findViewById(R.id.btnUpdate);
        dates = new AddMarks_LvView();
        dates = (AddMarks_LvView) getIntent().getSerializableExtra("date");
        course = new Course();
        course = (Course) getIntent().getSerializableExtra("course");
        marksListview=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Marks");
        updateDate.setText(dates.getDate());
        courseView.setText(course.courseName);

        updateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UpdateMarksActivity.this,
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
                updateDate.setText(dateSelect);

            }
        };

       databaseReference.child(course.courseName).child(dates.date).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    updateMarksType.setText(snapshot.getKey());

                    Log.e("value",snapshot.getValue().toString());
                    marksListview.clear();
                    for (DataSnapshot studentSnapshort:snapshot.getChildren()){
                        Student studentList=studentSnapshort.getValue(Student.class);
                        marksListview.add(studentList);
                        adapter=new UpdateAdapter(UpdateMarksActivity.this,marksListview);
                        updateListView.setAdapter(adapter);
                        Log.e("listValues",studentList.toString());


                    }
                }






           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


        upButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        List<Student> marksLists = adapter.getMarksLists();
       String date1 = updateDate.getText().toString().replace("/","-");
        databaseReference.child(course.courseName).setValue(date1).toString();
        String marksType1 = updateMarksType.getText().toString().trim();
       databaseReference.child(course.courseName).child(date1).setValue(marksType1);


        for (int i = 0; i<= marksLists.size();i++){


            databaseReference.child(course.courseName).child(date1).child(marksType1).setValue(marksLists).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(UpdateMarksActivity.this, "Marks Update successfully", Toast.LENGTH_LONG).show();

                    }
                    else {

                        Toast.makeText(UpdateMarksActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        Intent intent = new Intent(UpdateMarksActivity.this,OptionDialogeActivity.class);
        startActivity(intent);
        finish();

    }



});


    }
}
