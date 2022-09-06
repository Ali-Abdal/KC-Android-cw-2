//package com.example.finalproject;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {
//
//    ArrayList<Doctors> doctorsArrayList;
//
//    public DoctorsAdapter(ArrayList<Doctors> doctorsArrayList) {
//        this.doctorsArrayList = doctorsArrayList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        Doctors doctors = doctorsArrayList.get(position);
//        holder.docimg.setImageResource(doctors.getPhoto());
//        holder.docnametxt.setText(doctors.getName());
//        holder.protxt.setText(doctors.getSpecialization());
//    }
//
//    @Override
//    public int getItemCount() {
//        return doctorsArrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView docimg;
//        TextView docnametxt, protxt;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            docimg = itemView.findViewById(R.id.imgdr);
//            docnametxt = itemView.findViewById(R.id.txtdrname);
//            protxt = itemView.findViewById(R.id.txtdrpro);
//
//        }
//    }
//}
