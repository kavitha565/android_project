package com.example.exchangelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserFeedAdapter userFeedAdapter;
    ArrayList<PostFeed> postFeedsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ArrayList<PostFeed> postFeedsList = createPostFeeds();
        recyclerView = findViewById(R.id.user_profile_feeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userFeedAdapter = new UserFeedAdapter(this,postFeedsList);
        recyclerView.setAdapter(userFeedAdapter);


        //Redirect to add post page
        Button addPostButton = (Button) findViewById(R.id.addPost);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, AddPost.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<PostFeed> createPostFeeds(){
        postFeedsList = new ArrayList<PostFeed>();
        postFeedsList.add(new PostFeed(
                "Test User",
                "Title: Letting Go",
                "Author: Philip Roth",
                "Summary: Letting Go by Philip Roth is a book my father handed me. Only one novel a year, on average, was read by Dad. He read hundreds of scholarly non-fiction books each year because he studied politics.",
                "Genre: Fictional",
                "Review: Good",
                "Rating: 4/5",
                "Location: Texas"));
        postFeedsList.add(new PostFeed(
                "Test User",
                "Title: Letting Go",
                "Author: Philip Roth",
                "Summary: Letting Go by Philip Roth is a book my father handed me. Only one novel a year, on average, was read by Dad. He read hundreds of scholarly non-fiction books each year because he studied politics.",
                "Genre: Fictional",
                "Review: Good",
                "Rating: 4/5",
                "Location: Texas"));
        postFeedsList.add(new PostFeed(
                "Test User",
                "Title: Letting Go",
                "Author: Philip Roth",
                "Summary: Letting Go by Philip Roth is a book my father handed me. Only one novel a year, on average, was read by Dad. He read hundreds of scholarly non-fiction books each year because he studied politics.",
                "Genre: Fictional",
                "Review: Good",
                "Rating: 4/5",
                "Location: Texas"));
        return postFeedsList;
    }
}