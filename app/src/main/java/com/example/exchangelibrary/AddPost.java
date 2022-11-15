package com.example.exchangelibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AddPost extends AppCompatActivity {
Spinner spinner;
String[] statuss = {"In hand","In-progress", "Completed", "Open to exchange", "Swapped"};
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        spinner= findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddPost.this, android.R.layout.simple_list_item_1, statuss);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        imageView = findViewById(R.id.bookCover);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value=adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(AddPost.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent.launch("image/*");
            }
        });

        Button postBtn = (Button) findViewById(R.id.post);
        postBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddPost.this, ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(getApplication(),"Post added successfully",Toast.LENGTH_LONG).show();
            }
        });

        ImageView goBack = (ImageView) findViewById(R.id.goBackTo);
        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddPost.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

}
    ActivityResultLauncher<String> getContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null){
                        imageView.setImageURI(result);

                    }
                }
            }
    );

}