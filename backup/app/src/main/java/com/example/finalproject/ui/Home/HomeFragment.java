package com.example.finalproject.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

//import com.example.finalproject.DoctorsAdapter;
import com.example.finalproject.DocsAdapter;
import com.example.finalproject.DoctorProfile;
import com.example.finalproject.Doctors;
import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DatabaseReference database;
    ViewPager2 viewPager2;
    ArrayList<Doctors> doctorsArrayList = new ArrayList<>();



    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View v = inflater.inflate(R.layout.fragment_home, container, false);








        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}