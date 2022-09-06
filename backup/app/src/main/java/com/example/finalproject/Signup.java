package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Signup extends AppCompatActivity implements View.OnClickListener {
   private FirebaseAuth mauth;
   private EditText emailetsu, nameetsu, addrsetsu, passetsu, phoneetsu;
   private Button signinbtn;
   private TextView login2;
   private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mauth = FirebaseAuth.getInstance();

        emailetsu =(EditText) findViewById(R.id.etemailLogin);
        nameetsu = (EditText) findViewById(R.id.etPasswordLogin);
        addrsetsu = (EditText) findViewById(R.id.etaddresssignup);
        passetsu = (EditText) findViewById(R.id.etpasssignup);
        phoneetsu = (EditText) findViewById(R.id.etphonesignup);
        progressBar = (ProgressBar) findViewById(R.id.progressBarsignup);

         signinbtn = (Button) findViewById(R.id.btnsignin);
         signinbtn.setOnClickListener(this);

         login2 = (TextView) findViewById(R.id.btnlogin2);
         login2.setOnClickListener(this);
}
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignin:
                registerUser();
                break;
            case R.id.btnlogin2:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private void registerUser() {
        String email = emailetsu.getText().toString().trim();
        String name = nameetsu.getText().toString().trim();
        String address = addrsetsu.getText().toString().trim();
        String pass = passetsu.getText().toString().trim();
        String age = phoneetsu.getText().toString().trim();

        if(name.isEmpty()){
            nameetsu.setError("Full name is required!");
            nameetsu.requestFocus();
            return;
        }
        if(address.isEmpty()){
            addrsetsu.setError("Address is required!");
            addrsetsu.requestFocus();
            return;

        }
        if(age.isEmpty()){
            phoneetsu.setError("Phone number is required!");
            phoneetsu.requestFocus();
            return;

        }
        if(email.isEmpty()){
            emailetsu.setError("Email is required!");
            emailetsu.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailetsu.setError("Please provide valid email!");
            emailetsu.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            passetsu.setError("Password is required!");
            passetsu.requestFocus();
            return;

        }
        if(pass.length() < 6){
            passetsu.setError("Min password length should be 6 characters!");
            passetsu.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mauth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           User user = new User(name, email, address, age,"" );
                           FirebaseDatabase.getInstance().getReference("Users")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {

                                           if(task.isSuccessful()){
                                               Toast.makeText(Signup.this, "User has been registered successfully!"
                                               , Toast.LENGTH_LONG).show();
                                               progressBar.setVisibility(View.GONE);
                                               startActivity(new Intent(Signup.this, Uploadimage.class));
                                           }
                                           else{
                                               Toast.makeText(Signup.this, "Failed to register!"
                                                       , Toast.LENGTH_LONG).show();
                                               progressBar.setVisibility(View.GONE);
                                           }
                                       }
                                   });
                       }else {
                           Toast.makeText(Signup.this, "Failed to register!"
                                   , Toast.LENGTH_LONG).show();
                           progressBar.setVisibility(View.GONE);

                       }
                    }
                });


}}