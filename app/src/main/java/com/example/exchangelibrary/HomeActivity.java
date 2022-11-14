package com.example.exchangelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    // ActionBarDrawerToggle toggle;
    private FirebaseAuth.AuthStateListener authStateListener;

    RecyclerView recyclerView;
    PostFeedAdapter postAdapter;
    ArrayList<PostFeed> postFeedsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        TextView username = (TextView) findViewById(R.id.tv_title);
//        Button logout = (Button) findViewById(R.id.btn_logout);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigration_open,R.string.navigration_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        mAuth = FirebaseAuth.getInstance();



//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//            }
//        };
//
//        if (user != null) {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            String uid = user.getUid();
//            username.setText(name);
//        } else {
//            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });

        ArrayList<PostFeed> postFeedsList = createPostFeeds();
        recyclerView = findViewById(R.id.post_feeds_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postAdapter = new PostFeedAdapter(this,postFeedsList);
        recyclerView.setAdapter(postAdapter);


        //Redirect to chat page
        FloatingActionButton chatButton = (FloatingActionButton) findViewById(R.id.chatBtn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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