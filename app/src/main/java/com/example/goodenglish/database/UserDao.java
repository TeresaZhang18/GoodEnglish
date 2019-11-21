package com.example.goodenglish.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    //create user
    @Insert
    void createUser (User user);

    //check user
    @Query("SELECT USERNAME FROM USER WHERE userName = :name AND userPassword = :password ")
    String getUser (String name, String password);


}
