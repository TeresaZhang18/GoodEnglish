package com.example.goodenglish.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.goodenglish.QuizAdapter;
import com.example.goodenglish.R;
import com.example.goodenglish.database.MyQuiz;
import com.example.goodenglish.database.User;
import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.database.WordExplanation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    public Spinner level;
    public Spinner area;

    public String selectedlevel;
    public String selectedarea;

    public Button start;

    RecyclerView recyclerView;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

//set level spinner
        level = v.findViewById(R.id.levelspinner);
        ArrayAdapter<CharSequence> leveladapter = ArrayAdapter.createFromResource(getContext(), R.array.spinnerlevel, R.layout.support_simple_spinner_dropdown_item);
        leveladapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        level.setAdapter(leveladapter);
        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedlevel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView = v.findViewById(R.id.quiz_recyc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);


//set area spinner
        area = v.findViewById(R.id.areaspinner);
        ArrayAdapter<CharSequence> areaaadpter = ArrayAdapter.createFromResource(getContext(), R.array.spinnerarea, R.layout.support_simple_spinner_dropdown_item);
        area.setAdapter(areaaadpter);
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedarea = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        start = v.findViewById(R.id.startbutton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedarea==null || selectedlevel==null){
                    Toast.makeText(getContext(), "Please select quiz level and area!",Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        getQuiz(selectedarea,selectedlevel);
                        Toast.makeText(getContext(), "Waiting quiz to come online.",Toast.LENGTH_SHORT).show();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Bad Internet connection, try again!",Toast.LENGTH_SHORT).show();

                    }

                    UserDatabase db = UserDatabase.getInstance(getContext());
                    String quiz = db.myQuizDao().returnQuiz(selectedarea,selectedlevel);
                    System.out.println(quiz);

                    Gson gson = new Gson();
                    ArrayList<MyQuiz.QuizList> quizlist = gson.fromJson(quiz, new TypeToken<ArrayList<MyQuiz.QuizList>>(){}.getType());

                    //test
                    System.out.println(quizlist.get(0).getOption());

                    QuizAdapter quizAdapter = new QuizAdapter();
                    recyclerView.setAdapter(quizAdapter);
                    quizAdapter.setData(quizlist);
                }
            }
        });



        return v;
    }

    public void getQuiz(String area, String level){
        final okhttp3.OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://twinword-word-association-quiz.p.rapidapi.com/type1/?area="+area+"&level="+level)
                .get()
                .addHeader("x-rapidapi-host", "twinword-word-association-quiz.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f470e9c56emsh2cf021cb26a2cc0p1f1bc1jsnb6e4869e808d")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //System.out.println(response.body().string());

                Gson gson = new Gson();
                String json = response.body().string();
                MyQuiz myQuiz = gson.fromJson(json, MyQuiz.class);

                UserDatabase db = UserDatabase.getInstance(getContext());
                db.myQuizDao().createQuiz(selectedarea,Integer.parseInt(selectedlevel),myQuiz.getQuizLists());

            }

        });

    }
}
