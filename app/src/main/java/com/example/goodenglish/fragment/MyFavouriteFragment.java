package com.example.goodenglish.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goodenglish.FavouriteAdapter;
import com.example.goodenglish.R;
import com.example.goodenglish.database.User;
import com.example.goodenglish.database.UserDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFavouriteFragment extends Fragment {
    RecyclerView recyclerView;


    public MyFavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_favourite, container, false);
        recyclerView = view.findViewById(R.id.fav_recyc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        FavouriteAdapter favouriteAdapter = new FavouriteAdapter();
        recyclerView.setAdapter(favouriteAdapter);
        UserDatabase db = UserDatabase.getInstance(getContext());
        favouriteAdapter.setData(db.wordExplanationDao().getAllWord(User.user));


        return view;
    }

}
