package com.example.goodenglish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goodenglish.database.User;
import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.fragment.ProfileFragment;

public class ChangePassword extends AppCompatActivity {
    public EditText pass;
    public Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        pass = findViewById(R.id.changepass);

        change = findViewById(R.id.change_confirm);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = pass.getText().toString();

                Context context = v.getContext();
                UserDatabase db = UserDatabase.getInstance(context);
                db.userDao().changePassword(pass1, User.user);
                Toast.makeText(getApplicationContext(), "Change successfully!",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(ChangePassword.this, MainActivity.class));

            }
        });

    }
}
