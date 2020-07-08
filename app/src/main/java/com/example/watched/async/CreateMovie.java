package com.example.watched.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.watched.database.AppDatabase;
import com.example.watched.database.Movie;
import com.example.watched.util.OnAsyncEventListener;


public class CreateMovie extends AsyncTask<Movie, Void, Void> {

    private AppDatabase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateMovie(Context context, OnAsyncEventListener callback) {
        database = AppDatabase.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Movie... params) {
        try {
            for (Movie movie : params)
                database.movieDao().insertMovie(movie);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}
