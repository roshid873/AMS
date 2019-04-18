package com.example.roshi.backup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReportAdapter extends ArrayAdapter<AddMarks_LvView> {

    private Activity context;
    private List<AddMarks_LvView> dateLists;


    public ReportAdapter(Activity context, List<AddMarks_LvView>dateList)
    {
        super(context,R.layout.dateslist,dateList);
        this.context=context;
        this.dateLists=dateList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View dateListItem = inflater.inflate(R.layout.dateslist,null,true);


        TextView marksDate = dateListItem.findViewById(R.id.dates);

        AddMarks_LvView dateOfMarks= dateLists.get(position);


        marksDate.setText(dateOfMarks.getDate());

        return dateListItem;
    }


}
