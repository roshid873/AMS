package com.example.roshi.backup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    private static final String TAG = "ChangeMarksActivity";
    TextView textView;
    ListView listView;
    Course course;
    DatabaseReference databaseReference;
    List<AddMarks_LvView>dateDataList;



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
                    ChangeMatksAdapter adapter =new ChangeMatksAdapter(ChangeMarksActivity.this,dateDataList);
                    listView.setAdapter(adapter);


                }

                Log.d("data", "onDataChange() called with: v = [" + dateDataList + "]");
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChangeMarksActivity.this,UpdateMarksActivity.class);
                intent.putExtra("Date", dateDataList.get(position));
                intent.putExtra("course",course);
                startActivity(intent);
            }
        });

    }
}
