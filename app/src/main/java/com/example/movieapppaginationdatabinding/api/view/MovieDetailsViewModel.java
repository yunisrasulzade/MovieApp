package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;

public class MovieDetailsViewModel extends AndroidViewModel {
    private MovieDetailsRepository repository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDetailsRepository(application);
    }
    public LiveData<MovieDetails> getMovieDetails(int movieId){
        return repository.getMutableLiveData(movieId);
    }
}
