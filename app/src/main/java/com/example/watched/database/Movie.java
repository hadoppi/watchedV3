package com.example.watched.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Movie {

    public Movie(String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtimeMinutes, String genres) {
      
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
    }

    //    alphanumeric unique identifier of the title
    @PrimaryKey @NonNull
   public String tconst;
    //    the type/format of the title (e.g. movie, short, tvseries, tvepisode, video, etc)
    @ColumnInfo(name="Titre")
    public  String titleType;
    //    the more popular title / the title used by the filmmakers on promotional materials at the point of release
    @ColumnInfo(name="PseudoTitre")
    public  String primaryTitle;
    //    original title, in the original language
    @ColumnInfo(name="Titre original")
    public   String originalTitle;
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

    public Movie(String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtimeMinutes, ArrayList<String> genres) {
    }
}
