package com.example.exchangelibrary;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserFeedAdapter userFeedAdapter;
    String name, userId;
    TextView profile_name;
    ImageView profile_image;
    ArrayList<PostFeed> postFeedsList = new ArrayList<PostFeed>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("postFeeds");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //ArrayList<PostFeed> postFeedsList = createPostFeeds();
        recyclerView = findViewById(R.id.user_profile_feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.showPostFeeds();

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
            userId = user.getUid();
            profile_name.setText(name);

        }

        Log.e("Name", "" + name);

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

    private void showPostFeeds() {

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        Log.e("data", "" + dataSnapshot.getValue());
                        for (DataSnapshot ALL_USERS : dataSnapshot.getChildren()) {
                            String dbUserId = ALL_USERS.child("userId").getValue().toString();
                            String username = ALL_USERS.child("username").getValue().toString();
                            if(dbUserId.equals(userId)){
                                String title = ALL_USERS.child("title").getValue().toString();
                                String author = ALL_USERS.child("author").getValue().toString();
                                String summary = ALL_USERS.child("summary").getValue().toString();
                                String genre = ALL_USERS.child("genre").getValue().toString();
                                String review = ALL_USERS.child("review").getValue().toString();
                                String rating = ALL_USERS.child("rating").getValue().toString();
                                String location = ALL_USERS.child("location").getValue().toString();
                                String coverpage = ALL_USERS.child("coverPage").getValue().toString();
                                String status = ALL_USERS.child("status").getValue().toString();
                                postFeedsList.add(new PostFeed(userId, username, title, author, summary, genre, review, rating, status, location,coverpage));
                                userFeedAdapter = new UserFeedAdapter(ProfileActivity.this, postFeedsList);
                                recyclerView.setAdapter(userFeedAdapter);
                            }

                        }
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

//    private ArrayList<PostFeed> createPostFeeds(){
//        postFeedsList = new ArrayList<PostFeed>();
//        postFeedsList.add(new PostFeed(
//                "Kavitha Pasupuleti",
//                "Title: Letting Go",
//                "Author: Philip Roth",
//                "Summary: Letting Go by Philip Roth is a book my father handed me. Only one novel a year, on average, was read by Dad. He read hundreds of scholarly non-fiction books each year because he studied politics.",
//                "Genre: Fictional",
//                "Review: Good",
//                "Rating: 4/5",
//                "Status: In hand",
//                "Location: Texas"));
//        return postFeedsList;
//    }

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
