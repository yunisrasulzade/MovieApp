package com.example.movieapppaginationdatabinding.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.similar.SimilarResult;
import com.example.movieapppaginationdatabinding.databinding.SimiliarMoviesCardBinding;
import com.example.movieapppaginationdatabinding.ui.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.ViewHolder> {
    private List<SimilarResult> similiar;
    private Context context;

    public SimilarAdapter(List<SimilarResult> similiar, Context context) {
        this.similiar = similiar;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SimiliarMoviesCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.similiar_movies_card, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimilarResult similarResult = similiar.get(position);
        holder.moviesCardBinding.setSimiliarMovies(similarResult);

        holder.moviesCardBinding.similarMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("yunis", "onClick: " + position);
                Log.d("yunis", "onClick: " + similarResult.getId());
                try {
                    Intent intent = new Intent(context, MovieDetailsActivity.class);
                    intent.putExtra("movieId", similarResult.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                } catch (Exception e) {
                    Log.d("yunis", "onClick: " + e);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return similiar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimiliarMoviesCardBinding moviesCardBinding;

        public ViewHolder(SimiliarMoviesCardBinding binding) {
            super(binding.getRoot());
            this.moviesCardBinding = binding;
        }
    }
}
