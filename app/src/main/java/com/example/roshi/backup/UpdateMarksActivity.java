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
import java.util.Date;
import java.util.List;

public class UpdateMarksActivity extends AppCompatActivity {
    TextView updateDate,updateStudenId,updateMarks;
    EditText updateMarksType;
    ListView updateListView;
    Button button;
    Course course;
    AddMarks_LvView dates;
    DatabaseReference databaseReference;
    List<Student>marksListview;
    private UpdateAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_marks);
        updateDate=findViewById(R.id.tvUpdateDate);
        updateStudenId=findViewById(R.id.tvUpdateStudenId);
        updateMarks=findViewById(R.id.tvUpdateMarks);
        updateMarksType=findViewById(R.id.etUpdateMarkType);
        updateListView=findViewById(R.id.updateListView);
        button=findViewById(R.id.btnUpdate);
        dates = new AddMarks_LvView();
        dates = (AddMarks_LvView) getIntent().getSerializableExtra("date");
        course = new Course();
        course = (Course) getIntent().getSerializableExtra("course");
        marksListview=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Marks");
        updateDate.setText(dates.getDate());



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

//        databaseReference.child(course.courseName).child(dates.date).child(marksDatabase.getMarkType()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                marksListview.clear();
//                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
//                    Student studentList=studentSnapshot.getValue(Student.class);
//                    marksListview.add(studentList);
//                    adapter=new UpdateAdapter(UpdateMarksActivity.this,marksListview);
//                    updateListView.setAdapter(adapter);
//                    AddMarks_LvView updateMarksType = new AddMarks_LvView();
//                    updateMarksType.setMarkType(dates.getMarkType());
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



    }
}
