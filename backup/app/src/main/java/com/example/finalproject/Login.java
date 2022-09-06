package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {


    private TextView signup, forgotpass;
    private EditText editTextemail,editTextpass;
    private Button login;
    private FirebaseAuth mauth;
    private ProgressBar progressBarlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(this);

        forgotpass = (TextView) findViewById(R.id.textView10);
        forgotpass.setOnClickListener(this);

        login = (Button) findViewById(R.id.btnlogin);
        login.setOnClickListener(this);

        editTextemail = (EditText) findViewById(R.id.etemailLogin);
        editTextpass = (EditText) findViewById(R.id.etPasswordLogin);
        progressBarlogin = (ProgressBar) findViewById(R.id.progressBar);
        mauth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                startActivity(new Intent(this, Signup.class));
               break;
            case R.id.btnlogin:
                userLogin();
                break;
            case R.id.textView10:
                startActivity(new Intent(this, ForgotPass.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextemail.getText().toString().trim();
        String pass = editTextpass.getText().toString().trim();

        if(email.isEmpty()){
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please enter a valid email!");
            editTextemail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            editTextpass.setError("Password is required!");
            editTextpass.requestFocus();
            return;
        }
        if(pass.length() < 6){
            editTextpass.setError("Min password length is 6 characters!");
            editTextpass.requestFocus();
            return;
        }
        progressBarlogin.setVisibility(View.VISIBLE);

        mauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else {
                       user.sendEmailVerification();
                        progressBarlogin.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(Login.this, Verification.class));
                    }

                }else{
                    Toast.makeText(Login.this, "Failed to login!", Toast.LENGTH_LONG ).show();
                    progressBarlogin.setVisibility(View.INVISIBLE);

                }
            }
        });

    }
}