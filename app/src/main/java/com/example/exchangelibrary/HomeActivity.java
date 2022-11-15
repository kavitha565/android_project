package com.example.exchangelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ListView listView;
    ArrayList<String>  initName = new ArrayList<>(Arrays.asList("Kavitha Pasupuleti", "Sibangee Mohanty", "Harsh Muniwala", "Rishab"));
    ArrayList<String>  name = new ArrayList<>(Arrays.asList("Kavitha Pasupuleti", "Sibangee Mohanty", "Harsh Muniwala", "Rishab"));
    ArrayList<String>  tempArr = name;
    boolean isSearching = false;
    ArrayAdapter<String> arrayAdapter;
    View feed,searchfeed;
    SearchView searchView;
    RecyclerView recyclerView;
    PostFeedAdapter postAdapter;
    ArrayList<PostFeed> postFeedsList;
    // ActionBarDrawerToggle toggle;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        feed = findViewById(R.id.feedview);
        searchfeed = findViewById(R.id.searchinfo);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigration_open,R.string.navigration_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mAuth = FirebaseAuth.getInstance();

        listView = findViewById(R.id.listview);
        arrayAdapter =new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,tempArr);
        listView.setAdapter(arrayAdapter);
        tempArr.clear();

        ArrayList<PostFeed> postFeedsList = createPostFeeds();
        recyclerView = findViewById(R.id.post_feeds_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postAdapter = new PostFeedAdapter(this,postFeedsList);
        recyclerView.setAdapter(postAdapter);

        FloatingActionButton chatButton = (FloatingActionButton) findViewById(R.id.chatBtn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.actionsearch);
        menuItem.setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        isSearching = true;
                        return false;
                    }
                }
        );
        tempArr.clear();
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                feed.setVisibility(View.GONE);

                Log.e("Testing",""+newText);
                tempArr.clear();
                arrayAdapter.notifyDataSetChanged();
                Log.e("Testng", " Init name size is " + initName.size());
                for (int i = 0; i< initName.size(); i++) {
                    Log.e("Testng", " Item is " + initName.get(i).toString());
                    if(initName.get(i).toLowerCase().contains(newText.toString().toLowerCase())){
                        arrayAdapter.add(initName.get(i));
                    }
                }
                Log.e("Testng", " tempArr is " + tempArr.toString());
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.onActionViewCollapsed();
                feed.setVisibility(View.VISIBLE);
                searchfeed.setVisibility(View.GONE);
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            feed.setVisibility(View.GONE);
            searchView.setIconified(true);
        } else {
            feed.setVisibility(View.VISIBLE);
            super.onBackPressed();
        }
    }

    private ArrayList<PostFeed> createPostFeeds(){
        postFeedsList = new ArrayList<PostFeed>();
        postFeedsList.add(new PostFeed(
                "Kavitha Pasupuleti",
                "Title: Ikigai",
                "Author: Hector Gracia",
                "Summary: In researching this book, the authors interviewed the residents of the Japanese village.",
                "Genre: Philosophy",
                "Review: Good for self learning",
                "Rating: 4/5",
                "Status In hand",
                "Location: Texas"));
        postFeedsList.add(new PostFeed(
                "Sibangee Mohanty",
                "Title: The Intelligent Investor",
                "Author: Benjamin Graham",
                "Summary: Over the years, market developments have proven the wisdom of Graham's strategies. While preserving the integrity of Graham's original text, this revised edition includes updated commentary by noted financial journalist Jason Zweig, whose perspective incorporates the realities of today's market, draws parallels between Graham's examples and today's financial headlines. ",
                "Genre: Economics",
                "Review: Best for personal finance",
                "Rating: 5/5",
                "Status In hand",
                "Location: Texas"));
        postFeedsList.add(new PostFeed(
                "Harsh Muniwala",
                "Title: Atomic Habits",
                "Author: James Clear",
                "Summary: If you're having trouble changing your habits, the problem isn't you. The problem is your system. Bad habits repeat themselves again and again not because you don't want to change, but because you have the wrong system for change. You do not rise to the level of your goals. You fall to the level of your systems.",
                "Genre: Self-help",
                "Review: Average",
                "Rating: 3.5/5",
                "Status In hand",
                "Location: Texas"));
        return postFeedsList;
    }
}