package com.example.watched.rerpository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.watched.async.CreateMovie;
import com.example.watched.async.DeleteMovie;
import com.example.watched.async.UpdateMovie;
import com.example.watched.database.AppDatabase;
import com.example.watched.database.Movie;
import com.example.watched.util.OnAsyncEventListener;

import java.util.List;

public class MovieRepository {


    private static MovieRepository instance;

    private MovieRepository() {
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            synchronized (MovieRepository.class) {
                if (instance == null) {
                    instance = new MovieRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<Movie> getMovie(final String title, Context context) {
        return AppDatabase.getInstance(context).movieDao().findByTitle(title);
    }

    public LiveData<List<Movie>> getAllMovies(Context context) {
        return AppDatabase.getInstance(context).movieDao().selectAll();
    }

    public void insert(final Movie Movie, OnAsyncEventListener callback, Context context) {
        new CreateMovie(context, callback).execute(Movie);
    }

    public void update(final Movie Movie, OnAsyncEventListener callback, Context context) {
        new UpdateMovie(context, callback).execute(Movie);
    }

    public void delete(final Movie Movie, OnAsyncEventListener callback, Context context) {
        new DeleteMovie(context, callback).execute(Movie);
    }
}


