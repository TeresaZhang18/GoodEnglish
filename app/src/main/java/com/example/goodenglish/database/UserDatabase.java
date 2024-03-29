package com.example.goodenglish.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//this real db is used to store different user accounts information
@Database(entities = {User.class, WordExplanation.class, MyQuiz.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract WordExplanationDao wordExplanationDao();
    public abstract MyQuizDao myQuizDao();

    public static UserDatabase userDatabase;

    public static UserDatabase getInstance(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context, UserDatabase.class,"appdb").allowMainThreadQueries().build();
        }
        return userDatabase;
    }
}
