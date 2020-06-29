package com.example.movieapppaginationdatabinding.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.databinding.MovieCardBinding;
import com.example.movieapppaginationdatabinding.ui.MovieDetailsActivity;

public class PopularAdapter extends PagedListAdapter<Result, PopularAdapter.ViewHolder> {
    private Context context;

    public PopularAdapter(Context context) {
        super(Result.callback);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieCardBinding cardBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.movie_card, parent, false);

        return new ViewHolder(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        Result result = getItem(position);
        holder.movieCardBinding.setResult(result);

        holder.movieCardBinding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movieId",result.getId());
                context.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MovieCardBinding movieCardBinding;

        public ViewHolder(@NonNull MovieCardBinding movieCardBinding) {
            super(movieCardBinding.getRoot());
            this.movieCardBinding = movieCardBinding;
        }
    }
}
