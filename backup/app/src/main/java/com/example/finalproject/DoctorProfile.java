package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DoctorProfile extends AppCompatActivity {
    ImageView drprofileimg;
    TextView drnameprofiletxt, drphoneprofiletxt, drbioprofiletxt, drproprofiletxt, dremailprofiletxt;
    ArrayList<Doctors> doctorsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        //wolf noob
        drprofileimg = findViewById(R.id.imgdoctorprofile);
        drbioprofiletxt = findViewById(R.id.txtdrbioprofile);
        drnameprofiletxt = findViewById(R.id.txtdrnameprofile);
        drphoneprofiletxt = findViewById(R.id.hadiphone);
        drproprofiletxt = findViewById(R.id.txtdrproprofile);

        Bundle bundle = getIntent().getExtras();
        String docname = bundle.getString("name");
        drnameprofiletxt.setText( "Dr." +  docname);
        String docpro = bundle.getString("pro");
        drproprofiletxt.setText(docpro);
        String docbio = bundle.getString("bio");
        drbioprofiletxt.setText(docbio);
        int docimg = bundle.getInt("pic");
        drprofileimg.setImageResource(docimg);
        int docphone = bundle.getInt("num");
        drphoneprofiletxt.setText(String.valueOf(docphone));

        drphoneprofiletxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +docphone));
                startActivity(intent2);

            }
        });








    }


}