package me.tittojose.dagger2tutorial.screens;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.tittojose.dagger2tutorial.R;
import me.tittojose.dagger2tutorial.model.Movie;

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemMovieTitle;
        ImageView imgViewItemMoviePoster;

        MyViewHolder(View view) {
            super(view);
            tvItemMovieTitle = (TextView) view.findViewById(R.id.tvItemMovieTitle);
            imgViewItemMoviePoster = (ImageView) view.findViewById(R.id.imgViewItemMoviePoster);

        }
    }


    MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        String url = "http://image.tmdb.org/t/p/w342/" + movie.posterPath;
        holder.tvItemMovieTitle.setText(movie.title);

        Glide.with(holder.imgViewItemMoviePoster.getContext())
                .load(url)
                .centerCrop()
                .into(holder.imgViewItemMoviePoster);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
