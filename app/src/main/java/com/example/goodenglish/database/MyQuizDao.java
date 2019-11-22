package com.example.goodenglish.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.ArrayList;

@Dao
public interface MyQuizDao {
    @Query("INSERT INTO MyQuiz (area, level, quizLists) VALUES (:area,:level,:quizlist)")
    void createQuiz (String area, int level, ArrayList<MyQuiz.QuizList> quizlist);

    @Query("SELECT quizLists FROM MyQuiz WHERE area=:area and level=:level")
    @TypeConverters(Converters.class)
    String returnQuiz (String area, String level);
}
