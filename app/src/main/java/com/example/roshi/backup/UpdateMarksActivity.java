package com.example.roshi.backup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

public class UpdateMarksActivity extends AppCompatActivity {
    TextView updateDate,updateStudenId,updateMarks;
    EditText updateMarksType;
    ListView updateListView;
    Button button;
    Course course;
    Date date;

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
//        date = new Date();
//        course = (com.example.roshi.backup.) getIntent().getSerializableExtra("course");
        course = new Course();
        course = (com.example.roshi.backup.Course) getIntent().getSerializableExtra("course");    }
}
