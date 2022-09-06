package com.example.finalproject.ui.Pharmacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentAboutusBinding;

public class PharmacyFragment extends Fragment {

    private FragmentAboutusBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}