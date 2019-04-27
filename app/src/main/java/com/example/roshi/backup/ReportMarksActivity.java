package com.example.roshi.backup;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReportMarksActivity extends AppCompatActivity {

    TextView reportMarksDate,reportMarksStudenId,reportMarksMark,reportMarksType,courseView;
    ListView reportMarksListView;
    List<Student> reportListview;

    Course course;
    AddMarks_LvView dates;
    DatabaseReference databaseReference;
    private ReportMarksListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_marks);
        courseView=findViewById(R.id.tvReportMarksCourseView);
        reportMarksDate=findViewById(R.id.tvReportMarksDate);
        reportMarksStudenId=findViewById(R.id.tvReportMarksStudenId);
        reportMarksMark=findViewById(R.id.tvReportMarksMarks);
        reportMarksType=findViewById(R.id.tvReportMarksMarkType);
        reportMarksListView=findViewById(R.id.reportMarksListView);
        dates = new AddMarks_LvView();
        dates = (AddMarks_LvView) getIntent().getSerializableExtra("date");
        course = new Course();
        reportListview=new ArrayList<>();
        course = (Course) getIntent().getSerializableExtra("course");
        databaseReference= FirebaseDatabase.getInstance().getReference("Marks");
        reportMarksDate.setText(dates.getDate());
        courseView.setText(course.courseName);


        databaseReference.child(course.courseName).child(dates.date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    reportMarksType.setText(snapshot.getKey());

                    Log.e("value",snapshot.getValue().toString());
                    reportListview.clear();
                    for (DataSnapshot studentSnapshort:snapshot.getChildren()){
                        Student studentList=studentSnapshort.getValue(Student.class);
                        reportListview.add(studentList);
                        adapter=new ReportMarksListAdapter(ReportMarksActivity.this,reportListview);
                        reportMarksListView.setAdapter(adapter);
                        Log.e("listValues",studentList.toString());


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
