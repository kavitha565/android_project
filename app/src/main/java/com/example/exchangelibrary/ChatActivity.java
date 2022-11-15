package com.example.exchangelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PublicKey;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    ArrayList<ChatMessage> messagesList;
    String name;

    //ChatMessage messagesList = new ChatMessage("Hi this is kavitha","Kavitha Pasupuleti");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Log.d("info","In chat acitiviy");

        ArrayList<ChatMessage> messagesList = createMessagesList();

        FloatingActionButton sendBtn = (FloatingActionButton)findViewById(R.id.send);
        recyclerView = findViewById(R.id.list_of_messages);
        //recyclerView.hasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chatAdapter = new ChatAdapter(this,messagesList);
        recyclerView.setAdapter(chatAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
        };

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                Log.e("test",""+input.getText().toString());
                myRef.setValue(new ChatMessage(input.getText().toString(), "kavitha pasupuleti","14-11-2022 08:57PM"));
                messagesList.add(new ChatMessage(input.getText().toString(), "kavitha pasupuleti","14-11-2022 08:57PM"));

                chatAdapter = new ChatAdapter(ChatActivity.this,messagesList);
                recyclerView.setAdapter(chatAdapter);

                input.setText("");
            }
        });
        displayChatMessages();

        ImageView goBack = (ImageView) findViewById(R.id.goBackTo);
        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayChatMessages() {

    }

    private ArrayList<ChatMessage> createMessagesList(){
        messagesList = new ArrayList<ChatMessage>();
        messagesList.add(new ChatMessage("Hi I would like to explore some fictional books. Any suggestions?","Kavitha Pasupuleti","14-11-2022 07:04 PM"));
        messagesList.add(new ChatMessage("I can help you with that","Sibangee Mohanty","14-11-2022  07:34 PM"));
        messagesList.add(new ChatMessage("@Kavitha, Hi I want to Ikigai book and I am ready for an exchange","Harsh Munawala","14-11-2022 08:11 PM"));
        return messagesList;
    }

}