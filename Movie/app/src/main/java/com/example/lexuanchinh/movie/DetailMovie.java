package com.example.lexuanchinh.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lexuanchinh.movie.entity.Movie;

import static com.example.lexuanchinh.movie.API.APIService.BASE_IMAGES_URL;
import static com.example.lexuanchinh.movie.API.APIService.POSTER_SIZE;

public class DetailMovie extends AppCompatActivity {
    TextView txtTitle,txtRelaseDate,txtOverview;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Movie movie= (Movie) getIntent().getSerializableExtra("moive");
       // Toast.makeText(DetailMovie.this, movie.getReleaseDate(), Toast.LENGTH_SHORT).show();

        txtTitle=findViewById(R.id.txtTitle);
        txtRelaseDate=findViewById(R.id.txtRelaseDate);
        txtOverview=findViewById(R.id.txtOverview);
        img=findViewById(R.id.imgThumbnail);
        Glide.with(DetailMovie.this).load(BASE_IMAGES_URL+POSTER_SIZE+movie.getBackdropPath())
                .into(img);
        txtTitle.setText(movie.getTitle());
        txtRelaseDate.setText(movie.getReleaseDate());
        txtOverview.setText(movie.getOverview());
    }
}
