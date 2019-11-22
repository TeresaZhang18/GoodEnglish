package com.example.goodenglish;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodenglish.database.UserDatabase;
import com.example.goodenglish.database.WordExplanation;
import com.example.goodenglish.fragment.SearchWordsFragment;

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
    }

    @Override
    public int getItemCount() {
        return wordToAdapt.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public TextView wordname;
        public ImageButton delete;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            wordname = itemView.findViewById(R.id.list_wordname);
            delete = itemView.findViewById(R.id.list_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    UserDatabase db = UserDatabase.getInstance(context);
                    db.wordExplanationDao().deleteWord(wordname.getText().toString());
                    if (wordToAdapt.size()>0) {
                        wordToAdapt.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                }
            });

        }
    }
}
