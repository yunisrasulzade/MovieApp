package com.example.movieapppaginationdatabinding.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.adapter.SearchAdapter;
import com.example.movieapppaginationdatabinding.api.view.MovieViewModel;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.api.view.SearchViewModel;
import com.example.movieapppaginationdatabinding.databinding.SearchFragmentBinding;

import java.util.List;

public class SearchFragment extends Fragment {
    private SearchFragmentBinding binding;
    private MovieViewModel movieViewModel;
    private Context context;
    String TAG = "searchFragment";

    public SearchFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


//        binding = DataBindingUtil.setContentView((Activity) context, R.layout.popular_fragment);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.search_fragment, container, false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        binding.searchRecyclerView.setLayoutManager(layoutManager);
        try {
            String key = getArguments().getString("key");

            getMovies(key);

        } catch (Exception e) {
            Log.d(TAG, "onCreateView: " + e);
        }


        return view;
    }

    private void getMovies(String key) {

        SearchViewModel movieViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        movieViewModel.getLiveData(key).observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                    SearchAdapter adapter = new SearchAdapter(context, results);
                    binding.searchRecyclerView.setAdapter(adapter);
                    binding.searchProgress.setVisibility(View.INVISIBLE);
            }
        });

    }
}
