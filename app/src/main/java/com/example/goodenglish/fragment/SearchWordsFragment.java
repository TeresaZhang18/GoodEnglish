package com.example.goodenglish.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.database.WordAssociation;
import com.example.goodenglish.database.WordExample;
import com.google.gson.Gson;


import com.example.goodenglish.R;
import com.example.goodenglish.database.WordExplanation;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchWordsFragment extends Fragment {
    public EditText word;
    public String myword;
    public ImageButton search, add;
    public TextView noun, verb, adverb, adjective, example1, example2, association;

    public WordExplanation thisword;
    public WordExample thiswordexample;
    public WordAssociation thiswordasso;


    public SearchWordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_words, container, false);
        noun = view.findViewById(R.id.noun);
        verb = view.findViewById(R.id.verb);
        adverb = view.findViewById(R.id.adverb);
        adjective = view.findViewById(R.id.adjective);
        example1 = view.findViewById(R.id.example1);
        example2 = view.findViewById(R.id.example2);
        association = view.findViewById(R.id.association);

        word = view.findViewById(R.id.wordEntered);

//search the word that user entered
        search = view.findViewById(R.id.searchWord);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    myword = word.getText().toString();
                    runtask(myword);

//this thread.sleep is used to wait for the working thread to get all content back
//and assign a value to variable "thisword"
                    Thread.sleep(2000);

                    noun.setText(thisword.getMeaning().getNoun());
                    verb.setText(thisword.getMeaning().getVerb());
                    adverb.setText(thisword.getMeaning().getAdverb());
                    adjective.setText(thisword.getMeaning().getAdjective());

                    example1.setText(thiswordexample.getExample().get(0));
                    example2.setText(thiswordexample.getExample().get(1));

                    //get all associated words
                    String assoword = "";
                    for (int i=0; i<thiswordasso.getAssoc_word().size(); i++) {
                         String asso = thiswordasso.getAssoc_word().get(i);
                         assoword += asso+"; ";
                    }
                    association.setText(assoword);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Bad Internet connection, try again!",Toast.LENGTH_SHORT).show();
                }
            }
        });

//add the word to favourite list
        add = view.findViewById(R.id.addFavourite);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Context context = v.getContext();
                    UserDatabase db = UserDatabase.getInstance(context);
                    db.wordExplanationDao().addWord(thisword);
                    Toast.makeText(getContext(), "Word added successfully!",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Word already added!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

//loading data from internet in a new working thread
    public void runtask(String word) throws Exception{

        final okhttp3.OkHttpClient client = new OkHttpClient();

//send http request for word definition
        final Request request = new Request.Builder()
                .url("https://twinword-word-graph-dictionary.p.rapidapi.com/definition/?entry="+word)
                .get()
                .addHeader("x-rapidapi-host", "twinword-word-graph-dictionary.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f470e9c56emsh2cf021cb26a2cc0p1f1bc1jsnb6e4869e808d")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                //for error checking
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                System.out.println(response.body().toString());

                Gson gson = new Gson();
                String responseString = response.body().string();
                thisword = gson.fromJson(responseString, WordExplanation.class);
            }

        });

//send http request for word example
        final Request request2 = new Request.Builder()
                .url("https://twinword-word-graph-dictionary.p.rapidapi.com/example/?entry="+word)
                .get()
                .addHeader("x-rapidapi-host", "twinword-word-graph-dictionary.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f470e9c56emsh2cf021cb26a2cc0p1f1bc1jsnb6e4869e808d")
                .build();

        client.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                //for error checking
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                System.out.println(response.body().toString());

                Gson gson = new Gson();
                String responseString = response.body().string();
                thiswordexample = gson.fromJson(responseString, WordExample.class);
            }

        });

//send http request for associated word
        final Request request3 = new Request.Builder()
                .url("https://twinword-word-graph-dictionary.p.rapidapi.com/association/?entry="+word)
                .get()
                .addHeader("x-rapidapi-host", "twinword-word-graph-dictionary.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f470e9c56emsh2cf021cb26a2cc0p1f1bc1jsnb6e4869e808d")
                .build();

        client.newCall(request3).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                //for error checking
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                System.out.println(response.body().toString());

                Gson gson = new Gson();
                String responseString = response.body().string();
                thiswordasso = gson.fromJson(responseString, WordAssociation.class);
            }

        });

    }

}
