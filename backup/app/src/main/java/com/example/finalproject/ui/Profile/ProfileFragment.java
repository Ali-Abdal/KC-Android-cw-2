package com.example.finalproject.ui.Profile;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Login;
import com.example.finalproject.R;
import com.example.finalproject.Signup;
import com.example.finalproject.User;
import com.example.finalproject.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private FirebaseAuth mauth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String Userid;
    private ImageView imgbtn,signupimg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView logout = root.findViewById(R.id.Logoutbtn);
        mauth = FirebaseAuth.getInstance();


        ProgressBar progressBar2 = root.findViewById(R.id.progressBarsignup2);



        progressBar2.setVisibility(View.VISIBLE);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth.signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        Userid = user.getUid();

        final  TextView emailtxt = (TextView) root.findViewById(R.id.profileemail);
        final  TextView nametxt = (TextView) root.findViewById(R.id.profilename);
        final  TextView agetxt = (TextView) root.findViewById(R.id.profileage);
        final  TextView addresstxt = (TextView) root.findViewById(R.id.profileaddress);

        reference.child(Userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.getFullname();
                    String email = userProfile.getEmail();
                    String age = userProfile.getAge();
                    String address = userProfile.getAddress();

                    emailtxt.setText(email);
                    nametxt.setText(name);
                    agetxt.setText(age);
                    addresstxt.setText(address);

                    progressBar2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });




        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}