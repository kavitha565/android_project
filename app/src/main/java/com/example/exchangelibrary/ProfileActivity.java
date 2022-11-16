package com.example.exchangelibrary;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserFeedAdapter userFeedAdapter;
    ArrayList<PostFeed> postFeedsList;
    String name;
    TextView profile_name;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ArrayList<PostFeed> postFeedsList = createPostFeeds();
        recyclerView = findViewById(R.id.user_profile_feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userFeedAdapter = new UserFeedAdapter(this,postFeedsList);
        recyclerView.setAdapter(userFeedAdapter);

        profile_image = findViewById(R.id.profileimage);

//        profile_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getContent.launch("image/*");
//            }
//        });

        profile_name = findViewById(R.id.profilename);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
            profile_name.setText(name);

        }

        Log.e("Name",""+name);

        //Redirect to add post page
        Button addPostButton = (Button) findViewById(R.id.addPost);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, AddPost.class);
                startActivity(intent);
            }
        });

        ImageView goBack = (ImageView) findViewById(R.id.goBackTo);
        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<PostFeed> createPostFeeds(){
        postFeedsList = new ArrayList<PostFeed>();
        postFeedsList.add(new PostFeed(
                "Kavitha Pasupuleti",
                "Title: Letting Go",
                "Author: Philip Roth",
                "Summary: Letting Go by Philip Roth is a book my father handed me. Only one novel a year, on average, was read by Dad. He read hundreds of scholarly non-fiction books each year because he studied politics.",
                "Genre: Fictional",
                "Review: Good",
                "Rating: 4/5",
                "Status: In hand",
                "Location: Texas"));
        return postFeedsList;
    }

//    ActivityResultLauncher<String> getContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
//            new ActivityResultCallback<Uri>() {
//                @Override
//                public void onActivityResult(Uri result) {
//                    if(result != null){
//                        profile_image.setImageURI(result);
//
//                    }
//                }
//            }
//    );
}