package com.example.finalproject.ui.Hospitals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.ui.Mapfragments.Map_FragmentJahra;
import com.example.finalproject.ui.Mapfragments.Map_fragmentMubark;

public class Mubarkhos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mubarkhos);
        Fragment fragment = new Map_fragmentMubark();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, fragment).commit();


        TextView txtq = findViewById(R.id.hadiphone);


        txtq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 12290902));
                startActivity(intent2);
            }
        });
    }
}