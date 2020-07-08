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

import java.util.List;


public class MovieListViewModel extends AndroidViewModel {

    private MovieRepository repository;

    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Movie>> observableMovies;

    public MovieListViewModel(@NonNull Application application, MovieRepository movieRepository) {
        super(application);

        repository = movieRepository;

        applicationContext = application.getApplicationContext();

        observableMovies = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableMovies.setValue(null);

        LiveData<List<Movie>> Movies = repository.getAllMovies(applicationContext);

        // observe the changes of the entities from the database and forward them
        observableMovies.addSource(Movies, observableMovies::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final MovieRepository movieRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            movieRepository = MovieRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieListViewModel(application, movieRepository);
        }
    }

    /**
     * Expose the LiveData MovieEntities query so the UI can observe it.
     */
    public LiveData<List<Movie>> getMovies() {
        return observableMovies;
    }
}
