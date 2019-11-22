package com.example.goodenglish.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<MyQuiz.QuizList> fromString(String value) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MyQuiz.QuizList>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<MyQuiz.QuizList> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
