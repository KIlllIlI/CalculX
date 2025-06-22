package com.example.calculmentalliamvassetnathanbonnard;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreDAO {
    @Insert
    void insert(Score score);

    @Update
    public void update(Score score);
    @Delete
    void delete(Score score);

    @Query("SELECT * FROM scores")
    List<Score> getAll();

    @Query("SELECT * FROM scores ORDER BY score DESC")
    List<Score> getClassement();

    @Query("SELECT * FROM scores ORDER BY score ASC LIMIT 1")
    List<Score> getWorstGuy();
}