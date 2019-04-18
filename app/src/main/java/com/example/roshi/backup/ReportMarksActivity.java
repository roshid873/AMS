package com.example.roshi.backup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ReportMarksActivity extends AppCompatActivity {

    TextView reportMarksDate,reportMarksStudenId,reportMarksMark;
    EditText reportMarksType;
    ListView reportMarksListView;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_marks);
        reportMarksDate=findViewById(R.id.tvReportMarksDate);
        reportMarksStudenId=findViewById(R.id.tvReportMarksStudenId);
        reportMarksMark=findViewById(R.id.tvReportMarksMarks);
        reportMarksType=findViewById(R.id.etReportMarksMarkType);
        reportMarksListView=findViewById(R.id.reportMarksListView);
    }
}
