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
    @Query("SELECT userName FROM User WHERE userName = :name AND userPassword = :password ")
    String getUser (String name, String password);

    @Query("UPDATE User SET userPassword=:pass WHERE userName=:name")
    void changePassword (String pass, String name);


}
