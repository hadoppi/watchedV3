package com.example.watched.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.watched.database.Movie;
import com.example.watched.rerpository.MovieRepository;
import com.example.watched.util.OnAsyncEventListener;


public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<Movie> observableMovie;

    public MovieViewModel(@NonNull Application application,
                           final String email, MovieRepository MovieRepository) {
        super(application);

        repository = MovieRepository;

        applicationContext = application.getApplicationContext();

        observableMovie = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableMovie.setValue(null);

        LiveData<Movie> Movie = repository.getMovie(email, applicationContext);

        // observe the changes of the Movie entity from the database and forward them
        observableMovie.addSource(Movie, observableMovie::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String email;

        private final MovieRepository repository;

        public Factory(@NonNull Application application, String MovieEmail) {
            this.application = application;
            this.email = MovieEmail;
            repository = MovieRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieViewModel(application, email, repository);
        }
    }

    /**
     * Expose the LiveData Movie query so the UI can observe it.
     */
    public LiveData<Movie> getMovie() {
        return observableMovie;
    }

    public void createMovie(Movie Movie, OnAsyncEventListener callback) {
        repository.insert(Movie, callback, applicationContext);
    }

    public void updateMovie(Movie Movie, OnAsyncEventListener callback) {
        repository.update(Movie, callback, applicationContext);
    }

    public void deleteMovie(Movie Movie, OnAsyncEventListener callback) {
        repository.delete(Movie, callback, applicationContext);
    }
}
