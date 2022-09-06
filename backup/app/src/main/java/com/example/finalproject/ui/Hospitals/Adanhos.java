package com.example.finalproject.ui.Hospitals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.ui.Mapfragments.Map_fragmentAdan;

public class Adanhos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adanhos);
        Fragment fragment = new Map_fragmentAdan();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout, fragment).commit();
        TextView phoneadan = findViewById(R.id.hadiphone);


        phoneadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 99838768));
                startActivity(intent2);
            }

        });

    }
}