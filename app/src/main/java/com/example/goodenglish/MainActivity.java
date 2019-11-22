package com.example.goodenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goodenglish.database.User;
import com.example.goodenglish.database.UserDatabase;

public class MainActivity extends AppCompatActivity {

    public Button login;
    public Button signup;
    public EditText username, userpassword;
    public String user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateUser.class));
            }
        });

        username = findViewById(R.id.userName);
        userpassword = findViewById(R.id.password);

        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                password = userpassword.getText().toString();

                try {
                    Context context = v.getContext();
                    UserDatabase db = UserDatabase.getInstance(context);
                    String thisuser = db.userDao().getUser(user,password);
                    String thispass = db.userDao().getPassword(user,password);

                    if (thisuser.equals(user) && thispass.equals(password)) {
                        User.user = thisuser;
                        Intent intent = new Intent(context, MainPage.class);
                        intent.putExtra("user", thisuser);
                        context.startActivity(intent);
                    } else  {
                        Toast.makeText(getApplicationContext(), "Incorrect user name or password.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Incorrect user name or password.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
