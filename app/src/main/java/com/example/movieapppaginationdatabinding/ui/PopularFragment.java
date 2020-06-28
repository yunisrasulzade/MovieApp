package com.example.movieapppaginationdatabinding.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.Handler;
import com.example.movieapppaginationdatabinding.adapter.PopularAdapter;
import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.view.MovieViewModel;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.databinding.PopularFragmentBinding;

public class PopularFragment extends Fragment {
    private PopularFragmentBinding binding;
    private MovieViewModel movieViewModel;
    private Context context;
    private Handler handler;
    private ProgressBar progressBar;


    public PopularFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.popular_fragment, container, false);
        View view = binding.getRoot();

        progressBar = view.findViewById(R.id.popular_progress);

//        handler = new Handler();
//        handler.setLoading(true);
//        binding.setDataFetched(handler);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        binding.recyclerView.setLayoutManager(layoutManager);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        getMovies();

        return view;
    }

    public void getMovies() {
        movieViewModel.getLiveData().observe(this, results -> {
            if (results.size()!=0){
                PopularAdapter adapter = new PopularAdapter(context);
                adapter.submitList(results);
                binding.recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("rasul", "NOT: ");
            }
            else{
                Log.d("rasul", "YES: ");
            }
        });
    }
}
