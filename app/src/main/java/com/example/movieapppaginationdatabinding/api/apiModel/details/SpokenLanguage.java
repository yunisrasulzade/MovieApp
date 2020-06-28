package com.example.movieapppaginationdatabinding.api.apiModel.details;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.movieapppaginationdatabinding.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("name")
    @Expose
    private String name;

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
