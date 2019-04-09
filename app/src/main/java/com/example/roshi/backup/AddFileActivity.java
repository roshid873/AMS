package com.example.roshi.backup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class AddFileActivity extends AppCompatActivity {

    private static final String TAG = "AddFileActivity";

    private static final int REQUEST_CODE = 6384;

    Button buttonAddFile,buttonSaveFile;
    EditText editText;

    InputStream inputStream;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }
        buttonAddFile = findViewById(R.id.btnAddFile);
        buttonSaveFile =findViewById(R.id.btnSaveFile);
        editText = findViewById(R.id.fileName);

        buttonAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(AddFileActivity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true)
                        .start();
            }
        });
        buttonSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            editText.setText(filePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Permission not Granted",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }
        }
}
