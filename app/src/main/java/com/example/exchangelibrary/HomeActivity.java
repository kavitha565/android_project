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
    ArrayList<String>  initName = new ArrayList<>(Arrays.asList("Sibangee", "Rishab", "Kavitha","Harsh","aaaa"));
    ArrayList<String>  name = new ArrayList<>(Arrays.asList("Sibangee", "Rishab", "Kavitha","Harsh","aaaa"));
    ArrayList<String>  tempArr = name;
    boolean isSearching = false;
    ArrayAdapter<String> arrayAdapter;
    View scroll;
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
        scroll = findViewById(R.id.scrollview);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigration_open,R.string.navigration_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mAuth = FirebaseAuth.getInstance();

        listView = findViewById(R.id.listview);
        arrayAdapter =new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,tempArr);
        listView.setAdapter(arrayAdapter);
        tempArr.clear();
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
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                scroll.setVisibility(View.GONE);
                Log.e("Testing",""+newText);
//                arrayAdapter.getFilter().filter(newText);
                tempArr.clear();
                arrayAdapter.notifyDataSetChanged();
                Log.e("Testng", " Init name size is " + initName.size());
//                arrayAdapter.add("");
                for (int i = 0; i< initName.size(); i++) {
                    Log.e("Testng", " Item is " + initName.get(i).toString());
                    if(initName.get(i).toLowerCase().contains(newText.toString().toLowerCase())){
                        arrayAdapter.add(initName.get(i));
                        //                        tempArr.add(initName.get(i));
                    }
                }
                Log.e("Testng", " tempArr is " + tempArr.toString());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}