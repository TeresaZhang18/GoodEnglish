package com.example.goodenglish.database;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyQuiz {
    public String area;
    public int level;
    @SerializedName("quizlist")
    public ArrayList<QuizList> quizLists;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<QuizList> getQuizLists() {
        return quizLists;
    }

    public void setQuizLists(ArrayList<QuizList> quizLists) {
        this.quizLists = quizLists;
    }

    public class QuizList {

        public ArrayList<String> quiz;
        public ArrayList<String> option;
        public int correct;

        public ArrayList<String> getQuiz() {
            return quiz;
        }

        public void setQuiz(ArrayList<String> quiz) {
            this.quiz = quiz;
        }

        public ArrayList<String> getOption() {
            return option;
        }

        public void setOption(ArrayList<String> option) {
            this.option = option;
        }

        public int getCorrect() {
            return correct;
        }

        public void setCorrect(int correct) {
            this.correct = correct;
        }
    }
}
