package com.example.goodenglish.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodenglish.ChangePassword;
import com.example.goodenglish.MainActivity;
import com.example.goodenglish.R;
import com.example.goodenglish.database.User;
import com.example.goodenglish.database.UserDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public Button change, logout;
    public TextView name;
    public ImageView imageView;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = v.findViewById(R.id.profile_userName);
        name.setText(User.user);
        imageView = v.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.person);

        logout = v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        change = v.findViewById(R.id.changepass);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ChangePassword.class);
                context.startActivity(intent);
            }
        });

        return v;
    }

}
