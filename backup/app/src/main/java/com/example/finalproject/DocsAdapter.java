package com.example.finalproject;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class DocsAdapter extends ArrayAdapter {


    public DocsAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

    }
}
