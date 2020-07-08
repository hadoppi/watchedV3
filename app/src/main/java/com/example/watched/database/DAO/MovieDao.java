package com.example.watched.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.watched.database.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * from movie")
    LiveData<List<Movie>> selectAll();

    @Query("SELECT * from movie Where tconst= :id ")
    LiveData<Movie> findById(String id);

    @Query("SELECT * from movie Where Titre= :title ")
    LiveData<Movie>  findByTitle(String title);

    @Query("SELECT * from movie Where `Date de naissance`< :annee AND `Date de décès`> :annee ORDER by `Titre`")
    LiveData<List<Movie>> findByYear(int annee);

    @Query("DELETE FROM movie")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie m);

    @Delete
    void delete(Movie movie);

    @Update
    void update(Movie movie);
}
