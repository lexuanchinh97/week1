package com.example.lexuanchinh.movie.API;

import com.example.lexuanchinh.movie.entity.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/3/movie/now_playing")
    Call<MovieList> getNowPlaying(@Query("api_key") String API_KEY,
                                  @Query("page") int page,
                                  @Query("language") String language);
}
