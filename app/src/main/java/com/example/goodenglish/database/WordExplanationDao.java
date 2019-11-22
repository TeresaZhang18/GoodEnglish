package com.example.goodenglish.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordExplanationDao {

    @Query("INSERT INTO WordExplanation (entry, userName) VALUES (:word,:user)")
    void addWord(String word, String user);


    @Query("SELECT * FROM WORDEXPLANATION WHERE userName=:name")
    List<WordExplanation> getAllWord(String name);

    @Query("DELETE FROM WORDEXPLANATION WHERE entry=:name")
    void deleteWord(String name);
}
