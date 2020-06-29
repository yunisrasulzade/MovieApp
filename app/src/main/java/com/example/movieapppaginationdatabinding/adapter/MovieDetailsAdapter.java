package com.example.movieapppaginationdatabinding.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.apiModel.cast.Cast;
import com.example.movieapppaginationdatabinding.databinding.CastCardBinding;
import com.example.movieapppaginationdatabinding.ui.ActorDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {
    private List<Cast> casts;
    private Context context;

    public MovieDetailsAdapter(List<Cast> casts, Context context) {
        this.casts = casts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CastCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.cast_card, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = casts.get(position);
        holder.castCardBinding.setCast(cast);

        holder.castCardBinding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MovieDetailsAdapter", "Artist id: " + cast.getId());
                Intent intent = new Intent(context, ActorDetailsActivity.class);
                intent.putExtra("personId", cast.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CastCardBinding castCardBinding;

        public ViewHolder(@NonNull CastCardBinding binding) {
            super(binding.getRoot());
            this.castCardBinding = binding;
        }
    }
}
