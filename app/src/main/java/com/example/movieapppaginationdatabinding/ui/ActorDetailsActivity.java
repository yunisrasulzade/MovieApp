package com.example.movieapppaginationdatabinding.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapppaginationdatabinding.R;
import com.example.movieapppaginationdatabinding.api.Actor;
import com.example.movieapppaginationdatabinding.api.apiRoot.ApiService;
import com.example.movieapppaginationdatabinding.api.apiRoot.RetrofitClientInstance;
import com.example.movieapppaginationdatabinding.api.view.ActorDetailsViewModel;
import com.example.movieapppaginationdatabinding.databinding.ActivityActorDetailsBinding;
import com.example.movieapppaginationdatabinding.databinding.ActivityMovieDetailsBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorDetailsActivity extends AppCompatActivity {
    ActivityActorDetailsBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);

        detailsBinding = DataBindingUtil.setContentView(ActorDetailsActivity.this, R.layout.activity_actor_details);

        Intent intent = getIntent();
        int actorId = intent.getIntExtra("personId", 0);

        getActor(actorId);
    }

    public void getActor(int actorId) {
        ActorDetailsViewModel detailsViewModel = ViewModelProviders.of(this).get(ActorDetailsViewModel.class);
        detailsViewModel.getActorDetails(actorId).observe(this, new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {
                if (actor!=null){
                    detailsBinding.setActor(actor);

                    detailsBinding.fullname.setText(actor.getName());
                    detailsBinding.biography.setText(actor.getBiography());
                    detailsBinding.actorProgress.setVisibility(View.INVISIBLE);
                    detailsBinding.actorDetailsLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
