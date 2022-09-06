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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Uploadimage extends AppCompatActivity {
    private ImageView imgbtn,signupimg;
    private Uri imagePath;
    private FirebaseAuth mauth;
    private TextView upload;
    private Button btnnexttologin;
    private int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);

        mauth = FirebaseAuth.getInstance();


        imgbtn = (ImageView) findViewById(R.id.uploadimagesignup2);
        signupimg = (ImageView) findViewById(R.id.clientimagesignup2);
        upload = (TextView) findViewById(R.id.uploadbtnsignup2);
        btnnexttologin = (Button) findViewById(R.id.buttonuploadimage);

        btnnexttologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (x <= 0) {
                    Toast.makeText(Uploadimage.this, "Make sure to upload your picture!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Uploadimage.this, Login.class);
                    startActivity(intent);
                }
            }});


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photointent = new Intent(Intent.ACTION_PICK);
                photointent.setType("image/*");
                startActivityForResult(photointent, 1);

            }


        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imagePath = data.getData();
            getImageInImageView();
            x++;
        }
    }

    private void getImageInImageView() {

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        signupimg.setImageBitmap(bitmap);
    }

    private void uploadImage() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        FirebaseStorage.getInstance().getReference("images/" + UUID.randomUUID().toString()).putFile(imagePath).addOnCompleteListener(
                new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful()){
                                        updateProfilePicture(task.getResult().toString());
                                    }
                                }
                            });
                            Toast.makeText(Uploadimage.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Uploadimage.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = 100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded" + (int) progress + "%");
            }
        });

    }

    private void updateProfilePicture(String url){
        FirebaseDatabase.getInstance().getReference("Users/"+FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Photo")
                .setValue(url);
    }


}