package com.example.movieapppaginationdatabinding.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.adapter.PopularAdapter;
import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.api.view.MovieViewModel;
import com.example.movieapppaginationdatabinding.databinding.PopularFragmentBinding;

public class PopularFragment extends Fragment {
    private PopularFragmentBinding binding;
    private MovieViewModel movieViewModel;
    private Context context;

    public PopularFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.popular_fragment, container, false);
        View view = binding.getRoot();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        binding.recyclerView.setLayoutManager(layoutManager);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        final Observer<PagedList<Result>> observer = new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {

            }
        };

        getMovies();

        return view;
    }

    private void getMovies() {
        movieViewModel.getLiveData().observe(this, results -> {
            PopularAdapter adapter = new PopularAdapter(context);
            adapter.submitList(results);
            binding.recyclerView.setAdapter(adapter);
        });
    }
}
