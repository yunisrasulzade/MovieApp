package com.example.movieapppaginationdatabinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.movieapppaginationdatabinding.api.view.MovieViewModel;
import com.example.movieapppaginationdatabinding.databinding.ActivityMainBinding;
import com.example.movieapppaginationdatabinding.ui.PopularFragment;
import com.example.movieapppaginationdatabinding.ui.SearchFragment;

public class MainActivity extends AppCompatActivity {
    String TAG = "mainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        PopularFragment popularFragment = new PopularFragment(MainActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, popularFragment)
                .commit();


        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (charSequence.toString().isEmpty()) {
                        onBackPressed();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("key", charSequence.toString());
                        SearchFragment searchFragment = new SearchFragment(MainActivity.this);
                        searchFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, searchFragment)
                                .addToBackStack("search")
                                .commit();
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count > 0) {
            for (int i = 0; i < count; ++i) {

                getSupportFragmentManager().popBackStack();
            }
        } else {
            super.onBackPressed();
        }
    }
}



