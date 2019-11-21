package com.example.goodenglish.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//the explanation of word
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userName", childColumns = "userName"))
public class WordExplanation {
    @PrimaryKey
    @NonNull
    public String entry;

    @Ignore
    public Meaning meaning;

    public String userName;

    public class Meaning {

        public String noun;
        public String verb;
        public String adverb;
        public String adjective;

        public String getNoun() {
            return noun;
        }

        public void setNoun(String noun) {
            this.noun = noun;
        }

        public String getVerb() {
            return verb;
        }

        public void setVerb(String verb) {
            this.verb = verb;
        }

        public String getAdverb() {
            return adverb;
        }

        public void setAdverb(String adverb) {
            this.adverb = adverb;
        }

        public String getAdjective() {
            return adjective;
        }

        public void setAdjective(String adjective) {
            this.adjective = adjective;
        }
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Meaning getMeaning() {
        return meaning;
    }

    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
