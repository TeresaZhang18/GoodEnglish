package com.example.goodenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.database.User;

public class CreateUser extends AppCompatActivity {

    public Button back;
    public Button create;
    public EditText username;
    public EditText userpassword;

    public String name;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateUser.this, MainActivity.class));
            }
        });

        username = findViewById(R.id.createuserName);
        userpassword = findViewById(R.id.createpassword);

        create = findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                password = userpassword.getText().toString();

                User user = new User(name, password);
                try {
                    Context context = v.getContext();
                    UserDatabase db = UserDatabase.getInstance(context);
                    db.userDao().createUser(user);

                    Toast.makeText(getApplicationContext(), "User created!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "User name has been used, try another!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
