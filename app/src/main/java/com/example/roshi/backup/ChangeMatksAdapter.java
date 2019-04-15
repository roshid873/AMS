package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ChangeMatksAdapter  extends ArrayAdapter<AddMarks_LvView> {

    private Activity context;
    private List<AddMarks_LvView>dateLists;


    public ChangeMatksAdapter(Activity context, List<AddMarks_LvView>dateList)
    {
       super(context,R.layout.dateslist,dateList);
       this.context=context;
       this.dateLists=dateList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View dateListItem = inflater.inflate(R.layout.dateslist,null,true);


        TextView marksDate = dateListItem.findViewById(R.id.dates);

        final AddMarks_LvView dateOfMarks= dateLists.get(position);
        if (dateOfMarks.getDate() != null) {
           String setDate=dateOfMarks.getDate();
           marksDate.setText(setDate);
        }

       marksDate.setText(dateOfMarks.getDate());

        return dateListItem;
    }




}
