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
    VideoView videoView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.splash_layout);

//        videoView = findViewById(R.id.video_view);
//
//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
//        videoView.setVideoURI(video);
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//                startNextActivity();
//            }
//        });
//
//        videoView.start();
        startNextActivity();
    }

    private void startNextActivity() {
//        if (isFinishing())
//            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    }
