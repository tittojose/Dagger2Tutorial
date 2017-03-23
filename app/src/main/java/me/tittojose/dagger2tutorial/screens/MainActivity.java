package me.tittojose.dagger2tutorial.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.tittojose.dagger2tutorial.Dagger2TutorialApp;
import me.tittojose.dagger2tutorial.R;
import me.tittojose.dagger2tutorial.model.Movie;
import me.tittojose.dagger2tutorial.model.MoviesList;
import me.tittojose.dagger2tutorial.network.MovieAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MovieAPIService movieAPIService;
    Picasso picasso;

    Call<MoviesList> movieListRequest;

    RecyclerView rvMovies;
    private MoviesAdapter mAdapter;
    private List<Movie> mMovieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUi();

        movieAPIService = Dagger2TutorialApp.get(this).getMovieAPIService();
        picasso = Dagger2TutorialApp.get(this).getPicasso();

        movieListRequest = movieAPIService.getPopularMovies();

        movieListRequest.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                Toast.makeText(MainActivity.this, moviesList.results.size() + "", Toast.LENGTH_SHORT).show();
                mMovieList.addAll(moviesList.results);
//                mAdapter = new MoviesAdapter(mMovieList);
//                rvMovies.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });
    }

    private void initializeUi() {
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        mAdapter = new MoviesAdapter(mMovieList, picasso);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);


        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.setItemAnimator(new DefaultItemAnimator());
        rvMovies.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (movieListRequest != null) {
            movieListRequest.cancel();
        }
    }
}
