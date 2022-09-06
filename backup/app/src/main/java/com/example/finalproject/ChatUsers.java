package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatUsers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBarchat;
    private UsersAdapter usersAdapter;
    UsersAdapter.OnUserClickListener onUserClickListener;
    private SwipeRefreshLayout swipeRefreshLayout;
    String myImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users);

        progressBarchat = findViewById(R.id.progressBar3);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerchat1);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
                startActivity(new Intent(ChatUsers.this, MessegesAct.class)
                        .putExtra("username_of_roommate",users.get(position).getFullname())
                        .putExtra("email_of_roommate",users.get(position).getEmail())
                        .putExtra("img_of_roommate",users.get(position).getPhoto())
                        .putExtra("my_img",myImageUrl)


                );
            }
        };
        getUsers();
    }

    private void getUsers(){
        users.clear();
        FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    users.add(dataSnapshot1.getValue(User.class));
                }
                usersAdapter = new UsersAdapter(users, ChatUsers.this,onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(ChatUsers.this));
                recyclerView.setAdapter(usersAdapter);
                progressBarchat.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                for (User user: users){
                    if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        myImageUrl = user.getPhoto();
                        return;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}