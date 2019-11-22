package com.example.goodenglish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodenglish.database.MyQuiz;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private ArrayList<MyQuiz.QuizList> myquiz;

    public void setData (ArrayList<MyQuiz.QuizList> quizToAdapt) {
        myquiz = quizToAdapt;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list, parent, false);
        QuizViewHolder quizViewHolder =new QuizViewHolder(v);
        return quizViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        final MyQuiz.QuizList quizAtPosition = myquiz.get(position);

        String myoption = quizAtPosition.getOption().get(0)+",  "+quizAtPosition.getOption().get(1);
        holder.option.setText(myoption);
        String myquiz = quizAtPosition.getQuiz().get(0)+",  "+quizAtPosition.getQuiz().get(1)+",  "+quizAtPosition.getQuiz().get(2);
        holder.quiz.setText(myquiz);
        holder.answer.setText(String.valueOf(quizAtPosition.getCorrect()));

    }

    @Override
    public int getItemCount() {
        return myquiz.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        public TextView option, quiz, answer;
        public RadioButton b1, b2;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            option = itemView.findViewById(R.id.quiz_option);
            quiz = itemView.findViewById(R.id.quiz_quiz);
            answer = itemView.findViewById(R.id.quiz_answer);

            b1 = itemView.findViewById(R.id.radioButton1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer.setVisibility(View.VISIBLE);
                }
            });
            b2 = itemView.findViewById(R.id.radioButton2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answer.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
