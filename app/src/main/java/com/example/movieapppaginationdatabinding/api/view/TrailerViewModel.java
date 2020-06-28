package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;
import com.example.movieapppaginationdatabinding.api.apiModel.video.VideoRoot;

public class TrailerViewModel extends AndroidViewModel {
    private MovieDetailsRepository repository;

    public TrailerViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDetailsRepository(application);
    }
    public LiveData<VideoRoot> getTrailer(int movieId){
        return repository.getTrailerLiveData(movieId);
    }
}
