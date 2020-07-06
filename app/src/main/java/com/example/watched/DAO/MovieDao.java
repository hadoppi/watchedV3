package com.example.watched.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.watched.database.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * from movie Where tconst= :id ")
    Movie findById(String id);
    @Query("SELECT * from movie Where `Date de naissance`< :annee AND `Date de décès`> :annee ORDER by `Titre original`" )
    List<Movie> findByYear(int annee);
    @Query("DELETE FROM movie")
    void deleteAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie m);
}
