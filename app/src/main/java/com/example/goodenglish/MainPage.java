package com.example.goodenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;

import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.fragment.MyFavouriteFragment;
import com.example.goodenglish.fragment.ProfileFragment;
import com.example.goodenglish.fragment.QuizFragment;
import com.example.goodenglish.fragment.ResourcesFragment;
import com.example.goodenglish.fragment.SearchWordsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent intent = getIntent();

        String thisuser = intent.getStringExtra("user");
        UserDatabase db = UserDatabase.getInstance(this);

        Fragment fragment = new SearchWordsFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.search_nav) {
                    Fragment fragment = new SearchWordsFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.favourite_nav) {
                    Fragment fragment = new MyFavouriteFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.quiz_nav) {
                    Fragment fragment = new QuizFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.resource_nav) {
                    Fragment fragment = new ResourcesFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.profile_nav) {
                    Fragment fragment = new ProfileFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myfragment, fragment);
        fragmentTransaction.commit();
    }
}
