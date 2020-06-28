package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieViewModel extends AndroidViewModel {
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> liveData;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        MovieDataSourceFactory factory = new MovieDataSourceFactory(application, apiService);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(4)
                .setPageSize(20)
                .build();
        executor = Executors.newFixedThreadPool(5);
        liveData = (new LivePagedListBuilder<Integer, Result>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Result>> getLiveData() {
        return liveData;
    }
}
