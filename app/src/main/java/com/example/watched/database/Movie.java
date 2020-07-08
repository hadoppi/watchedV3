package com.example.watched.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {



    public Movie(String title, boolean isAdult, int startYear, int endYear, int runtimeMinutes, String genres) {
      
        this.title = title;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
    }

    //    alphanumeric unique identifier of the title
    @PrimaryKey @NonNull
   public String tconst;
    @ColumnInfo(name="Titre")
    public  String title;
    //    0: non-adult  1: adult
    @ColumnInfo(name="Film pour adultes ")
    public  boolean isAdult;
    //            (YYYY) – represents the release year of a title. In the case of TV Series, it is the series start year
    @ColumnInfo(name="Date de naissance")
    public  int startYear;
    //            (YYYY) – TV Series end year. ‘\N’ for all other title types
    @ColumnInfo(name="Date de décès")
    public  int endYear;
    //    primary runtime of the title, in minutes
    @ColumnInfo(name="Durée")
    public   int runtimeMinutes;
    //    includes up to three genres associated with the title
    @ColumnInfo(name="Style(s)")
    public  String genres;



}
