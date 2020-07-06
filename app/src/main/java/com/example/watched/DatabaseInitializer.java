package com.example.watched;

import android.os.AsyncTask;
import android.util.Log;

import com.example.watched.database.Movie;

import java.util.ArrayList;

public class DatabaseInitializer {
    private static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db)
    {
        Log.i(TAG, ">> Inserting Nate Smartphone data. WARNING");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    private static void addMovie(final AppDatabase db, final String titleType, final String primaryTitle, final String originalTitle, final boolean isAdult, final int startYear, final int endYear, final int runtimeMinutes, final ArrayList<String> genres)
    {
        Movie movie = new Movie(titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes, genres);
        db.movieDao().insertMovie(movie);
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {
        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db)
        {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            populateWithTestData(database);
            return null;
        }
    }
    private static void populateWithTestData(AppDatabase db) {
        db.movieDao().deleteAll();
        addMovie(db, "test", "test", "test", false, 2009, 2010, 200, new ArrayList<String>());
    }

}
