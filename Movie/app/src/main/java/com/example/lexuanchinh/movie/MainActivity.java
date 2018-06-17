package com.example.lexuanchinh.movie;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.lexuanchinh.movie.API.APIInterface;
import com.example.lexuanchinh.movie.API.APIService;
import com.example.lexuanchinh.movie.entity.Movie;
import com.example.lexuanchinh.movie.entity.MovieAdapter;
import com.example.lexuanchinh.movie.entity.MovieList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lexuanchinh.movie.API.APIService.BASE_IMAGES_URL;
import static com.example.lexuanchinh.movie.API.APIService.POSTER_SIZE;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    MovieAdapter adapter;
    List<Movie> movieList;
    MovieList list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    movieList=new ArrayList<>();
        recyclerView=  findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.
//                VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
       // MoveAdapter moveAdapter=new MoveAdapter(movieList, getApplicationContext());
      //  recyclerView.setAdapter(moveAdapter);
        if(movieList == null) {
            setUpListView();
            callAPI();

        } else {
            //there is already data? screen must be rotating or tab switching
           adapter.setData(movieList);
        }

    }
    private void callAPI() {
    //    progressBar.setVisibility(View.VISIBLE);

        APIService.getInstance().getNowPlaying(APIService.API_KEY, 1, Locale.getDefault().getLanguage()).enqueue(
                new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                     //   progressBar.setVisibility(View.GONE);
                        if (response.body() != null) {
                        list= response.body();
                        movieList=list.getMovie();
                        adapter.setData(movieList);
                        } else
                            Toast.makeText(MainActivity.this, response.message() != null ? response.message() : "Empty", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                      //  progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
    private void setUpListView() {
        movieList = new ArrayList<>();
       // recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new MovieAdapter(MainActivity.this);
        adapter.setData(movieList);
        adapter.setListener(new MovieAdapter.IClickListener() {
           @Override
           public void onItemClick(Movie movie) {
               //Toast.makeText(MainActivity.this, movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(MainActivity.this,DetailMovie.class);
               
               startActivity(intent);
           }
       });
        this.recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callAPI();
            }
        });
    }
}
