package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.PopularMovies;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private List<Result> list = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public SearchRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getSearchResult(String query){

        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<PopularMovies> call = apiService.getSearch(application.getApplicationContext().getString(R.string.key),query, 1);

        call.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                if (response.body()!=null){
                    mutableLiveData.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
