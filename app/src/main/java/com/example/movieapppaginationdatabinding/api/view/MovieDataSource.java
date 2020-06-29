package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.PopularMovies;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Result> {
    private ApiService apiService;
    private Application application;
    private String TAG = "movieDataSource";

    public MovieDataSource(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<PopularMovies> call = apiService.getPopularMovies(application.getApplicationContext().getString(R.string.key), "en-US", 1);

        call.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                List<Result> results = response.body().getResults();
                callback.onResult(results, null, 2);
           }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<PopularMovies> call = apiService.getPopularMovies(application.getApplicationContext().getString(R.string.key), "en-US", params.key);

        call.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                List<Result> results = response.body().getResults();
                callback.onResult(results, params.key + 1);

                Log.d(TAG, "onResponse: " + response.code());

            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {

            }
        });
    }
}