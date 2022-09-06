package com.example.finalproject.ui.Mapfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_fragmentMubark extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_, container, false);
        SupportMapFragment mapFragment=
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP );
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng Mubarkhospital = new LatLng(29.326647, 48.034868);
        map.addMarker(new MarkerOptions().position(Mubarkhospital).title("Mubarkhospital"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mubarkhospital, 15));

    }
}