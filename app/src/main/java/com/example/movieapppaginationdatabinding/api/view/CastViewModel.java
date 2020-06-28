package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapppaginationdatabinding.api.apiModel.cast.MoviCredit;
import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;

public class CastViewModel extends AndroidViewModel {
    private MovieDetailsRepository repository;

    public CastViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDetailsRepository(application);
    }
    public LiveData<MoviCredit> getMovieDetails(int movieId){
        return repository.getCast(movieId);
    }
}
