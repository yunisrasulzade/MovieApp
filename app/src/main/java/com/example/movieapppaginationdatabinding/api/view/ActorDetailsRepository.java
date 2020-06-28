package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.Actor;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorDetailsRepository {
    private MutableLiveData<Actor> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ActorDetailsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Actor> getActorDetailsLiveData(int personId) {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<Actor> call = apiService.getBio(personId);
        call.enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {
                try {
                    Actor actor = response.body();

                    mutableLiveData.setValue(actor);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Actor> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
