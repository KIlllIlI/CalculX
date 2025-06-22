package com.example.calculmentalliamvassetnathanbonnard;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores")
public class Score {

    public Score(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String pseudo;
    public int score;


}