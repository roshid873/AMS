package com.example.roshi.backup;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AddFileActivity extends AppCompatActivity {

    private static final String TAG = "AddFileActivity";

    private static final int REQUEST_CODE = 6384;

    InputStream inputStream;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);
    inputStream= getResources().openRawResource(R.raw.semple);
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    try {
        String csvLine;
        while ((csvLine = reader.readLine()) != null) {
            data = csvLine.split(",");
            try {
                //Log.e("Data",""+data[0]);
                Log.e("Data",""+data[0]+" "+data[1]);
            }
            catch (Exception e){
                Log.e("Problem",e.toString());
            }
        }
    }

    catch (IOException ex){
        throw new RuntimeException("Error in reading CSV file: "+ex);

    }


    }
}
