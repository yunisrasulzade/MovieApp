package com.example.movieapppaginationdatabinding.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapppaginationdatabinding.MainActivity;
import com.example.movieapppaginationdatabinding.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startNextActivity();
    }

    private void startNextActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }}
