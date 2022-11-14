package com.example.exchangelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPost extends AppCompatActivity {
Spinner spinner;
String[] statuss = {"In hand","In-progress", "Completed", "Open to exchange", "Swapped"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        spinner= findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddPost.this, android.R.layout.simple_list_item_1, statuss);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddPost.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

}

}