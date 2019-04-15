package com.example.roshi.backup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

public class ChangeMarksActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    Course course;
    DatabaseReference databaseReference;
    List<AddMarks_LvView>dateDataList;
    private ChangeMatksAdapter adaptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_marks);
        databaseReference= FirebaseDatabase.getInstance().getReference("Marks");

        textView=findViewById(R.id.tvdate);
        listView=findViewById(R.id.lvDateList);
        course = new Course();
        course = (Course) getIntent().getSerializableExtra("course");

        dateDataList = new ArrayList<>();
        databaseReference.child(course.getCourseName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dateDataList.clear();
                for (DataSnapshot date : dataSnapshot.getChildren()){

                    AddMarks_LvView list = date.getValue(AddMarks_LvView.class);
                    dateDataList.add(list);
                    adaptar=new ChangeMatksAdapter(ChangeMarksActivity.this,dateDataList);
                    listView.setAdapter(adaptar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
