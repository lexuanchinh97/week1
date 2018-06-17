package com.example.lexuanchinh.movie.entity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lexuanchinh.movie.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.lexuanchinh.movie.API.APIService.BASE_IMAGES_URL;
import static com.example.lexuanchinh.movie.API.APIService.POSTER_SIZE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

private List<Movie> data;
    private Context context;
    private IClickListener  listener;
    public MovieAdapter(Context ctx) {
        this.context = ctx;
    }

    /**
     * Replace movieList in the adapter
     *
     * @param data
     */
    public void setData(List<Movie> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * Sets the click listener
     * @param listener
     */
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    /**
     * Clear all movieList from adapter
     */
    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = data.get(position);
        if(movie!=null){
            holder.title.setText(movie.getTitle());
            holder.title_original.setText(movie.getOverview());
            //holder.releaseYear.setText(context.getString(R.string.row_released, movie.getReleaseDate()));

            //Debug only
            //Picasso.with(context).setIndicatorsEnabled(true);
            //Picasso.with(context).setLoggingEnabled(true);
            Glide.with(context).load(BASE_IMAGES_URL+POSTER_SIZE+movie.getPosterPath())
                    .into(holder.thumb_image);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for click listeners for this adapter
     */
    public interface IClickListener {
        void onItemClick(Movie movie);
    }

    /**
     * List row members
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView title_original;
        public ImageView thumb_image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
           title_original = (TextView) itemView.findViewById(R.id.txt_overview);
            thumb_image = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
}
