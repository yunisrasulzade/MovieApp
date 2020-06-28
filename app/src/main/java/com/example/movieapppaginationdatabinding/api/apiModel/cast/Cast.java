package com.example.movieapppaginationdatabinding.api.apiModel.cast;


import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.example.movieapppaginationdatabinding.BR;
import com.example.movieapppaginationdatabinding.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Cast extends BaseObservable {

    @SerializedName("cast_id")
    @Expose
    private Integer castId;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    @Bindable
    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.castId);
    }

    @Bindable
    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.character);
    }

    @Bindable
    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.creditId);

    }

    @Bindable
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.gender);

    }

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.id);

    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.name);

    }

    @Bindable
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.order);
    }

    @Bindable
    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
        notifyPropertyChanged(com.example.movieapppaginationdatabinding.BR.profilePath);
    }

    @BindingAdapter({"castPath"})
    public static void downloadImage(ImageView imageView, String uri) {
        Picasso.with(imageView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + uri)
                .error(R.drawable.placeholder2)
                .into(imageView);
    }

}