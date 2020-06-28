package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapppaginationdatabinding.adapter.MovieDetailsAdapter;
import com.example.movieapppaginationdatabinding.api.apiModel.cast.Cast;
import com.example.movieapppaginationdatabinding.api.apiModel.cast.MoviCredit;
import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;
import com.example.movieapppaginationdatabinding.api.apiModel.video.VideoRoot;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;
import com.example.movieapppaginationdatabinding.api.similar.Similar;
import com.example.movieapppaginationdatabinding.ui.MovieDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {
    private MutableLiveData<MovieDetails> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private MutableLiveData<Similar> similarMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<MoviCredit> castMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<VideoRoot> trailerLiveData = new MutableLiveData<>();

    public static String TAG = "yunis";

    public MovieDetailsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<MovieDetails> getMutableLiveData(int movieId) {
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<MovieDetails> call = apiService.getMovieDetails(movieId);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                try {
                    MovieDetails movieDetails = response.body();
                    mutableLiveData.setValue(movieDetails);

                    Log.d(TAG, "MovieDetails Response Code " + response.code());
                    Log.d(TAG, "getBackdropPath: " + response.body().getBackdropPath());
                } catch (Exception e) {
                    Log.d(TAG, "   getDetails() " + e);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<Similar> getSimilar(int movieId){
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<Similar> call = apiService.getSimilarMovies(movieId);

        call.enqueue(new Callback<Similar>() {
            @Override
            public void onResponse(Call<Similar> call, Response<Similar> response) {
                try {
                    similarMutableLiveData.setValue(response.body());

                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<Similar> call, Throwable t) {

            }
        });

        return similarMutableLiveData;
    }

    public MutableLiveData<MoviCredit> getCast(int movieId){
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<MoviCredit> call = apiService.getCast(movieId);

        call.enqueue(new Callback<MoviCredit>() {
            @Override
            public void onResponse(Call<MoviCredit> call, Response<MoviCredit> response) {
                try {
                    castMutableLiveData.setValue(response.body());
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e);
                }
            }

            @Override
            public void onFailure(Call<MoviCredit> call, Throwable t) {

            }
        });

        return castMutableLiveData;
    }

    public MutableLiveData<VideoRoot> getTrailerLiveData(int movieId){
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<VideoRoot> call = apiService.getTrailer(movieId);

        call.enqueue(new Callback<VideoRoot>() {
            @Override
            public void onResponse(Call<VideoRoot> call, Response<VideoRoot> response) {
                try {
                    trailerLiveData.setValue(response.body());
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + "**************************");
                }
            }

            @Override
            public void onFailure(Call<VideoRoot> call, Throwable t) {

            }
        });
        return trailerLiveData;
    }
}
