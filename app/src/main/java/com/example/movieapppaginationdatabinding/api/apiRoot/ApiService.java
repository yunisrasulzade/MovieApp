package com.example.movieapppaginationdatabinding.api.apiRoot;

import com.example.movieapppaginationdatabinding.api.Actor;
import com.example.movieapppaginationdatabinding.api.apiModel.cast.MoviCredit;
import com.example.movieapppaginationdatabinding.api.apiModel.details.MovieDetails;
import com.example.movieapppaginationdatabinding.api.apiModel.movie.PopularMovies;
import com.example.movieapppaginationdatabinding.api.apiModel.video.VideoRoot;
import com.example.movieapppaginationdatabinding.api.similar.Similar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular")
    Call<PopularMovies> getPopularMovies(@Query("api_key") String api_key,
                                         @Query("language") String lang,
                                         @Query("page") int number);

    @GET("search/movie")
    Call<PopularMovies> getSearch(@Query("api_key") String api_key,
                                  @Query("query") String query,
                                  @Query("page") int number);

    @GET("movie/{movieId}/credits?api_key=6455b4a645478f665c3e0e0d5cabf661")
    Call<MoviCredit> getCast(@Path("movieId")int movieId);

    @GET("movie/{movieId}?api_key=6455b4a645478f665c3e0e0d5cabf661")
    Call<MovieDetails> getMovieDetails(@Path("movieId")int movieId);

    @GET("movie/{movieId}/similar?api_key=6455b4a645478f665c3e0e0d5cabf661&language=en-US&page=1")
    Call<Similar> getSimilarMovies(@Path("movieId") int movieId);

    @GET("person/{personId}?api_key=6455b4a645478f665c3e0e0d5cabf661&language=en-US")
    Call<Actor> getBio(@Path("personId") int personId);

    @GET("movie/{movieId}/videos?api_key=6455b4a645478f665c3e0e0d5cabf661&language=en-US")
    Call<VideoRoot> getTrailer(@Path("movieId") int personId);




}
