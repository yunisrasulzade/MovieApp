package com.example.movieapppaginationdatabinding;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.movieapppaginationdatabinding.BR;

public class Handler extends BaseObservable {
    private boolean isLoading;

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.loading);
    }
}
