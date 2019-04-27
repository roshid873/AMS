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
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UpdateAdapter extends ArrayAdapter<Student> {
    private Activity context;
    private List<Student>updateMarksList;
    DatabaseReference databaseReference;

    public UpdateAdapter(Activity context, List<Student>updateMarksList){

        super(context,R.layout.add_marks_list,updateMarksList);
        this.context=context;
        this.updateMarksList=updateMarksList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View stMarksViewItem = inflater.inflate(R.layout.add_marks_list,null,true);
        databaseReference = FirebaseDatabase.getInstance().getReference("Marks");


        TextView stId = stMarksViewItem.findViewById(R.id.tvId);
        EditText stMarks= stMarksViewItem.findViewById(R.id.etmarks);

        final Student studentMarksList = updateMarksList.get(position);


        stId.setText(studentMarksList.getStId());
        stMarks.setText(String.valueOf(studentMarksList.getProvidedMark()));

        stMarks.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "20")});
        stMarks.addTextChangedListener(new TextWatcher() {


            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s != null) {
                    String s1 = s.toString();
                    if(!s1.isEmpty())
                        studentMarksList.setProvidedMark(Integer.valueOf(s1));
                }


            }
        });

        return stMarksViewItem;
    }

    public List<Student> getMarksLists() {
        return updateMarksList;
    }


}
