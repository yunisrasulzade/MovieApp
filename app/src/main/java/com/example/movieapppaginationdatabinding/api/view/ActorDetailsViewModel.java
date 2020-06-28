package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapppaginationdatabinding.api.Actor;

public class ActorDetailsViewModel extends AndroidViewModel {
    private ActorDetailsRepository detailsRepository;
    public ActorDetailsViewModel(@NonNull Application application) {
        super(application);
        detailsRepository = new ActorDetailsRepository(application);
    }

    public LiveData<Actor> getActorDetails(int actorId){

        return detailsRepository.getActorDetailsLiveData(actorId);
    }
}
