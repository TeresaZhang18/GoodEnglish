package com.example.goodenglish.database;

import java.util.ArrayList;

//the associated word with this word
public class WordAssociation {
    public String entry;
    public ArrayList<String> assoc_word;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public ArrayList<String> getAssoc_word() {
        return assoc_word;
    }

    public void setAssoc_word(ArrayList<String> assoc_word) {
        this.assoc_word = assoc_word;
    }
}
