package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.roshi.backup.Course;

import java.util.List;

public class CourseList extends ArrayAdapter<Course> {

    private Activity context;
    private List<Course>courseList;

    public CourseList(Activity context,List<Course> CourseList){
        super(context,R.layout.list_item,CourseList);
        this.context = context;
        this.courseList = CourseList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView courseName = listViewItem.findViewById(R.id.tvCourse);


        Course course = courseList.get(position);

        courseName.setText(course.getCourseName());


        return listViewItem;
    }


}
