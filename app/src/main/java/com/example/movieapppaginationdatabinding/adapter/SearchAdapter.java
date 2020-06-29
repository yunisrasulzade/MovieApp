package com.example.movieapppaginationdatabinding.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.databinding.MovieCardBinding;
import com.example.movieapppaginationdatabinding.ui.MovieDetailsActivity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Result> list;

    public SearchAdapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieCardBinding cardBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.movie_card, parent, false);

        return new ViewHolder(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.movieCardBinding.setResult(result);

        holder.movieCardBinding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("searchAdapter", "https://image.tmdb.org/t/p/w500/" + result.getPosterPath()+"    "+ result.getId());
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movieId",result.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        MovieCardBinding movieCardBinding;

        public ViewHolder(@NonNull MovieCardBinding movieCardBinding) {
            super(movieCardBinding.getRoot());
            this.movieCardBinding = movieCardBinding;
        }
    }
}
