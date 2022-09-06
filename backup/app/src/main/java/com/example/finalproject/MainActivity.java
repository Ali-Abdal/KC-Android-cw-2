package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalproject.ui.Hospitals.Adanhos;
import com.example.finalproject.ui.Hospitals.Hadihos;
import com.example.finalproject.ui.Hospitals.Jaberhos;
import com.example.finalproject.ui.Hospitals.Jahrahos;
import com.example.finalproject.ui.Hospitals.Mubarkhos;
import com.example.finalproject.ui.Hospitals.Seefhos;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    FirebaseAuth mauth;
    LinearLayout johndoc, lucasdoc, ameliadoc,andrewdoc,michealdoc,williamdoc, seefhos,
    adanhos, mubarkhos, jahrahos,hadihos, jaberhos ;
    TextView logout;
    ImageView btnshop;
    ArrayList<Doctors> doctorsArrayList = new ArrayList<>();
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mauth = FirebaseAuth.getInstance();

        logout = findViewById(R.id.Logoutbtn);

        johndoc = findViewById(R.id.doc_john);
        lucasdoc = findViewById(R.id.doc_lucas);
        ameliadoc = findViewById(R.id.doc_amelia);
        andrewdoc = findViewById(R.id.doc_andrew);
        michealdoc = findViewById(R.id.doc_michael);
        williamdoc = findViewById(R.id.doc_william);

        seefhos = findViewById(R.id.hos_seef);
        adanhos = findViewById(R.id.hos_adan);
        mubarkhos = findViewById(R.id.hos_mubark);
        jahrahos = findViewById(R.id.hos_jahra);
        hadihos = findViewById(R.id.hos_hadi);
        jaberhos = findViewById(R.id.hos_jaber);

        btnshop =findViewById(R.id.Shopbtn);

        johndoc.setOnClickListener(this::onClick);
        lucasdoc.setOnClickListener(this::onClick);
        ameliadoc.setOnClickListener(this::onClick);
        andrewdoc.setOnClickListener(this::onClick);
        michealdoc.setOnClickListener(this::onClick);
        williamdoc.setOnClickListener(this::onClick);

        seefhos.setOnClickListener(this::onClick);
        adanhos.setOnClickListener(this::onClick);
        mubarkhos.setOnClickListener(this::onClick);
        jahrahos.setOnClickListener(this::onClick);
        hadihos.setOnClickListener(this::onClick);
        jaberhos.setOnClickListener(this::onClick);
        btnshop.setOnClickListener(this::onClick);
//        logout.setOnClickListener(this::onClick);

        btnshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Shop.class));
            }
        });



        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChatUsers.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
        @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mauth.getCurrentUser();

       if (user == null){
            startActivity(new Intent(MainActivity.this, Login.class));
        }
       else if(!user.isEmailVerified()){
           startActivity(new Intent(MainActivity.this, Login.class));

       }
    }
    public void onClick(View v){
        if(v.equals(johndoc)){
            Intent intent = new Intent(MainActivity.this, DoctorProfile.class);

            Doctors doc1 = new Doctors("John", "doctor.john@gmail.com", "Anesthesiologists",
                    "An Anesthesiologists, graudated from University Of Glasgow. Has more than 5 years of expearince. Won the best doctor prize in Anesthesiology",
                    R.drawable.john,37749949);

            intent.putExtra("name", doc1.getName());
            intent.putExtra("email", doc1.getEmail());
            intent.putExtra("pro", doc1.getSpecialization());
            intent.putExtra("bio", doc1.getBio());
            intent.putExtra("pic", doc1.getPhoto());
            intent.putExtra("num", doc1.getDocphonenum());

            startActivity(intent);
        }
        else if(v.equals(lucasdoc)){
            Intent intent = new Intent(MainActivity.this, DoctorProfile.class);

            Doctors doc2 = new Doctors("Lucas", "doctor.Lucas@gmail.com", "Cardiologists",
                    "An Cardiologists, graudated from University Of Manchester. Has more than 9 years of expearince. Won the best doctor prize in Cardiology",
                    R.drawable.lucas,92809820);

            intent.putExtra("name", doc2.getName());
            intent.putExtra("email", doc2.getEmail());
            intent.putExtra("pro", doc2.getSpecialization());
            intent.putExtra("bio", doc2.getBio());
            intent.putExtra("pic", doc2.getPhoto());
            intent.putExtra("num", doc2.getDocphonenum());

            startActivity(intent);
        }
        else if(v.equals(ameliadoc)){


            Intent intent = new Intent(MainActivity.this, DoctorProfile.class);

            Doctors doc3 = new Doctors("Amelia", "doctor.Amelia@gmail.com", "Dermatologists",
                    "An Dermatologists, graudated from Universite de Paris. Has more than 6 years of expearince. Won the best doctor prize in Dermatology",
                    R.drawable.amelia,99671467);


            intent.putExtra("name", doc3.getName());
            intent.putExtra("email", doc3.getEmail());
            intent.putExtra("pro", doc3.getSpecialization());
            intent.putExtra("bio", doc3.getBio());
            intent.putExtra("pic", doc3.getPhoto());
            intent.putExtra("num", doc3.getDocphonenum());

            startActivity(intent);
        }
        else if(v.equals(andrewdoc)){

            Intent intent = new Intent(MainActivity.this, DoctorProfile.class);

            Doctors doc4 = new Doctors("Andrew", "doctor.Andrew@gmail.com", "Neurologist",
                    "An Neurologist, graudated from Harvard University. Has more than 10 years of expearince. Won the best doctor prize in Neurology",
                    R.drawable.andrew,98399092);

            intent.putExtra("name", doc4.getName());
            intent.putExtra("email", doc4.getEmail());
            intent.putExtra("pro", doc4.getSpecialization());
            intent.putExtra("bio", doc4.getBio());
            intent.putExtra("pic", doc4.getPhoto());
            intent.putExtra("num", doc4.getDocphonenum());

            startActivity(intent);
        }
        else if(v.equals(michealdoc)){


            Intent intent = new Intent(MainActivity.this, DoctorProfile.class);

            Doctors doc5 = new Doctors("Micheal", "doctor.Micheal@gmail.com", "Psychiatrists",
                    "An Psychiatrists, graudated from Stanford University. Has more than 12 years of expearince. Won the best doctor prize in Psychiatry",
                    R.drawable.michael,99882299);

            intent.putExtra("name", doc5.getName());
            intent.putExtra("email", doc5.getEmail());
            intent.putExtra("pro", doc5.getSpecialization());
            intent.putExtra("bio", doc5.getBio());
            intent.putExtra("pic", doc5.getPhoto());
            intent.putExtra("num", doc5.getDocphonenum());

            startActivity(intent);
        }
        else if(v.equals(williamdoc)){


            Intent intent = new Intent(MainActivity.this, DoctorProfile.class);

            Doctors doc6 = new Doctors("William", "doctor.William@gmail.com", "Pathologists",
                    "An Pathologists, graudated from University of Sheffield. Has more than 8 years of expearince. Won the best doctor prize in Pathology",
                    R.drawable.william,88192927);

            intent.putExtra("name", doc6.getName());
            intent.putExtra("email", doc6.getEmail());
            intent.putExtra("pro", doc6.getSpecialization());
            intent.putExtra("bio", doc6.getBio());
            intent.putExtra("pic", doc6.getPhoto());
            intent.putExtra("num", doc6.getDocphonenum());

            startActivity(intent);
        }
        else if(v.equals(adanhos)){
            Intent intent2 = new Intent(MainActivity.this, Adanhos.class);
            startActivity(intent2);
        }
        else if(v.equals(jaberhos)){
            Intent intent3 = new Intent(MainActivity.this, Jaberhos.class);
            startActivity(intent3);
        }
        else if(v.equals(jahrahos)){
            Intent intent4 = new Intent(MainActivity.this, Jahrahos.class);

            startActivity(intent4);
        }
        else if(v.equals(hadihos)){
            Intent intent5 = new Intent(MainActivity.this, Hadihos.class);
            startActivity(intent5);
        }
        else if(v.equals(mubarkhos)){
            Intent intent6 = new Intent(MainActivity.this, Mubarkhos.class);
            startActivity(intent6);
        }
        else if(v.equals(seefhos)){
            Intent intent7 = new Intent(MainActivity.this, Seefhos.class);
            startActivity(intent7);
        }

//        else if(v.equals(logout)){
//            mauth.signOut();
//            startActivity(new Intent(MainActivity.this, Login.class));
//
//        }


    }
}