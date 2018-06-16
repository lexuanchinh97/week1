package com.example.lexuanchinh.movie.API;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
  public static String API_KEY = "3b4c65c3780fc1ef44ec5500b186d833";
    public static String BASE_URL = "http://api.themoviedb.org";
    public static String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    public static String POSTER_SIZE = "w185";
//http://image.tmdb.org/t/p/w185/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg
    private static APIInterface instance = null;

    public static APIInterface getInstance() {
        if (instance == null) {
            synchronized (APIService.class) {
                if (instance == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.writeTimeout(15 * 60 * 1000, TimeUnit.MILLISECONDS);
                    httpClient.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
                    httpClient.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS);

                    OkHttpClient client = httpClient.build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                    instance = retrofit.create(APIInterface.class);
                }
            }
        }

        return  instance;
    }
}
