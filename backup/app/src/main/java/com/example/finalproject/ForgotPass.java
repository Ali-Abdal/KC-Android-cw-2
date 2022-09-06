package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {
    private EditText emailreset;
    private ProgressBar progressBarreset;
    private Button resetbtn;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        emailreset = (EditText) findViewById(R.id.etforgotpass);
        resetbtn = (Button) findViewById(R.id.btnresetpass);
        progressBarreset = (ProgressBar) findViewById(R.id.progressBarresetpass);

        auth = FirebaseAuth.getInstance();

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email = emailreset.getText().toString().trim();
        if(email.isEmpty()){
            emailreset.setError("Email is required");
            emailreset.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailreset.setError("Please provide valid email");
            emailreset.requestFocus();
            return;
        }
        progressBarreset.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPass.this, "Check your email to reset your password!",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPass.this, Login.class));
                }else {
                    progressBarreset.setVisibility(View.INVISIBLE);
                    Toast.makeText(ForgotPass.this, "Try again! Something wrong happened!",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}