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

public class Jahrahos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        Fragment fragment = new Map_FragmentJahra();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, fragment).commit();

        TextView txtg = findViewById(R.id.hadiphone);

        txtg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 17708899));
                startActivity(intent2);
            }
        });
    }
}