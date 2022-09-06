package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessegesAct extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private EditText msginput;
    private TextView txtchatname;
    private ProgressBar progressBar;
    private ImageView chatimg, imgsend;

    private MessageAdapter messageAdapter;
    private ArrayList<Message> messages;

    String usernameoftheroommate,emailOfroommate, chatRoomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messeges);


        usernameoftheroommate = getIntent().getStringExtra("username_of_roommate");
        emailOfroommate = getIntent().getStringExtra("email_of_roommate");

        recyclerView1 =  findViewById(R.id.recyxlermesseges);
        msginput = findViewById(R.id.edchat);
        txtchatname = findViewById(R.id.txtchatingwith);
        progressBar = findViewById(R.id.progressChat);
        chatimg = findViewById(R.id.img_toolbar);
        imgsend = findViewById(R.id.sendimg);

        txtchatname.setText(usernameoftheroommate);

        messages = new ArrayList<>();

        imgsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Messages/" + chatRoomId).push().setValue(new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),emailOfroommate,
                        msginput.getText().toString()));
                msginput.setText("");
            }
        });

        messageAdapter = new MessageAdapter(messages,getIntent().getStringExtra("my_img"),getIntent().getStringExtra("img_of_roommate"), MessegesAct.this);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(messageAdapter);

        Glide.with(MessegesAct.this).load(getIntent().getStringExtra("img_of_roommate")).placeholder(R.drawable.avatar).error(R.drawable.avatar).into(chatimg);

        setUpChatRoom();

    }

    private void setUpChatRoom(){
        FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String myUsername = dataSnapshot.getValue(User.class).getFullname();
                if(usernameoftheroommate.compareTo(myUsername)>0)
                {
                    chatRoomId = myUsername + usernameoftheroommate;
                }
                else if(usernameoftheroommate.compareTo(myUsername)== 0){
                    chatRoomId = myUsername + usernameoftheroommate;
                }
                else{
                    chatRoomId = usernameoftheroommate + myUsername;
                }
                attachMessagerListener(chatRoomId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void attachMessagerListener(String chatRoomId){
        FirebaseDatabase.getInstance().getReference("Messages/" + chatRoomId).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    messages.add(dataSnapshot1.getValue(Message.class));
                }
                messageAdapter.notifyDataSetChanged();
                recyclerView1.scrollToPosition(messages.size()-1);
                recyclerView1.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}