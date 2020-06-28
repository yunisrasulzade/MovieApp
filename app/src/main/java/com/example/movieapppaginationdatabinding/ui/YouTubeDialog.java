package com.example.movieapppaginationdatabinding.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.movieapppaginationdatabinding.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class YouTubeDialog extends DialogFragment {
    private Context context;

    public YouTubeDialog(Context context) {
        this.context = context;
    }

    com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView youTubePlayerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trailer_dialog_layout, container, false);
            youTubePlayerView = v.findViewById(R.id.player);
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer youTubePlayer) {

                    Bundle bundle = getArguments();
                    String videoId = bundle.getString("trailerKey");
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

        return v;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        try {
//            Dialog dialog = getDialog();
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        }
//        catch (Exception e){
//            Log.d("qasim", "onStart: " + e);
//        }
//
//    }
}
