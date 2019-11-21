package com.example.goodenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.database.WordExplanation;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private List<WordExplanation> wordToAdapt;

    public void setData (List<WordExplanation> myword){
        wordToAdapt = myword;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_list, parent, false);
        FavouriteViewHolder favouriteViewHolder = new FavouriteViewHolder(view);
        return favouriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        final WordExplanation wordposition =wordToAdapt.get(position);
        holder.wordname.setText(wordposition.getEntry());
//        holder.noun.setText(wordposition.getMeaning().getNoun());
//        holder.verb.setText(wordposition.getMeaning().getVerb());
//        holder.adv.setText(wordposition.getMeaning().getAdverb());
//        holder.adj.setText(wordposition.getMeaning().getAdjective());

    }

    @Override
    public int getItemCount() {
        return wordToAdapt.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public TextView wordname, noun, verb, adv, adj;
        public ImageButton delete;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            wordname = itemView.findViewById(R.id.list_wordname);
            noun = itemView.findViewById(R.id.list_noun);
            verb = itemView.findViewById(R.id.list_verb);
            adv = itemView.findViewById(R.id.list_adverb);
            adj = itemView.findViewById(R.id.list_adjective);
            delete = itemView.findViewById(R.id.list_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    UserDatabase db = UserDatabase.getInstance(context);
                    db.wordExplanationDao().deleteWord(wordname.getText().toString());
                }
            });
        }
    }
}
