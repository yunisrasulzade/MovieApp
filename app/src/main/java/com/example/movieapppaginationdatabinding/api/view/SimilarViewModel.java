package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;
import com.example.movieapppaginationdatabinding.api.similar.Similar;

public class SimilarViewModel extends AndroidViewModel {
    private MovieDetailsRepository repository;

    public SimilarViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDetailsRepository(application);
    }
    public LiveData<Similar> getSimilarMovies(int movieId){
        return repository.getSimilar(movieId);
    }
}
