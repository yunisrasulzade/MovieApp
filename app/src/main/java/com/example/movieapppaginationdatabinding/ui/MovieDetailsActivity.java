package com.example.movieapppaginationdatabinding.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapppaginationdatabinding.Handler;
import com.example.movieapppaginationdatabinding.adapter.MovieDetailsAdapter;
import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.adapter.SimilarAdapter;
import com.example.movieapppaginationdatabinding.api.apiModel.cast.MoviCredit;
import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.Result;
import com.example.movieapppaginationdatabinding.api.apiModel.video.VideoRoot;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;
import com.example.movieapppaginationdatabinding.api.similar.Similar;
import com.example.movieapppaginationdatabinding.api.view.CastViewModel;
import com.example.movieapppaginationdatabinding.api.view.MovieDetailsViewModel;
import com.example.movieapppaginationdatabinding.api.view.MovieViewModel;
import com.example.movieapppaginationdatabinding.api.view.SimilarViewModel;
import com.example.movieapppaginationdatabinding.api.view.TrailerViewModel;
import com.example.movieapppaginationdatabinding.databinding.ActivityMovieDetailsBinding;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    String TAG = "yunis";
    //    RecyclerView recyclerView;
    int movieId;
    String trailerKey=null;
    ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movieId = intent.getIntExtra("movieId", 0);

        Handler handler = new Handler();

        binding = DataBindingUtil.setContentView(MovieDetailsActivity.this, R.layout.activity_movie_details);
        binding.setHandler(handler);

        getCast();
        getDetails();
        getSimilarMovies();
        getTrailer();

    }

    public void showTrailer(View view) {
        if (trailerKey==null ||trailerKey.isEmpty()) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Trailer not found for this movie", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            DialogFragment dialogFragment = new YouTubeDialog(getApplicationContext());
            Bundle bundle = new Bundle();
            bundle.putString("trailerKey", trailerKey);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getSupportFragmentManager(), "dialog");
        }
    }

    public void getTrailer() {

        TrailerViewModel trailerViewModel = ViewModelProviders.of(this).get(TrailerViewModel.class);
        trailerViewModel.getTrailer(movieId).observe(this, new Observer<VideoRoot>() {
            @Override
            public void onChanged(VideoRoot videoRoot) {
                trailerKey = videoRoot.getResults().get(0).getKey();
            }
        });

    }

    public void getCast() {
        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        binding.castRecycler.setHasFixedSize(true);
        binding.castRecycler.setLayoutManager(manager);

        CastViewModel castViewModel = ViewModelProviders.of(this).get(CastViewModel.class);
        castViewModel.getMovieDetails(movieId).observe(this, new Observer<MoviCredit>() {
            @Override
            public void onChanged(MoviCredit moviCredit) {
                MovieDetailsAdapter movieDetailsAdapter = new MovieDetailsAdapter(moviCredit.getCast(), MovieDetailsActivity.this);
                binding.castRecycler.setAdapter(movieDetailsAdapter);
            }
        });



    }

    public void getDetails() {
        MovieDetailsViewModel movieViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieViewModel.getMovieDetails(movieId).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                binding.setMovie(movieDetails);
                binding.setGenres(movieDetails.getGenres().get(0));
            }
        });
    }

    public void getSimilarMovies() {
        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        binding.similarRecycler.setHasFixedSize(true);
        binding.similarRecycler.setLayoutManager(manager);

        SimilarViewModel similarViewModel = ViewModelProviders.of(this).get(SimilarViewModel.class);
        similarViewModel.getSimilarMovies(movieId).observe(this, new Observer<Similar>() {
            @Override
            public void onChanged(Similar similar) {
                SimilarAdapter similarAdapter = new SimilarAdapter(similar.getResults(), getApplicationContext());
                binding.similarRecycler.setAdapter(similarAdapter);
            }
        });


    }

    public String minuteToHour(int minute) {
        if (minute < 60) {
            return String.valueOf(minute);
        } else {
            String converted = (minute / 60) + "h " + (minute % 60) + "m";
            return converted;
        }


    }

}
