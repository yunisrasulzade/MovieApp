package com.example.movieapppaginationdatabinding.api.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private SearchRepository searchRepository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        searchRepository = new SearchRepository(application);
    }

    public LiveData<List<Result>> getLiveData(String query) {

        return searchRepository.getSearchResult(query);
    }
}
