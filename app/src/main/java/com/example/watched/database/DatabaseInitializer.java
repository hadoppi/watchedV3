package com.example.watched.database;

import android.os.AsyncTask;
import android.util.Log;



public class DatabaseInitializer {
    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    private static void addMovie(final AppDatabase db, final String title, final boolean isAdult, final int startYear, final int endYear, final int runtimeMinutes, String genre)
    {
        Movie movie = new Movie(title, isAdult, startYear, endYear, runtimeMinutes, genre);
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
        addMovie(db, "test", true,  2009, 2010, 200, "test");
        addMovie(db, "gameOfThrone", true,  2009, 2010, 200, "test");
        addMovie(db, "drHouse", true,  2009, 2010, 200, "test");
        addMovie(db, "FreshPrince", true,  2009, 2010, 200, "test");
        addMovie(db, "Lost", true,  2009, 2010, 200, "test");
    }

}
