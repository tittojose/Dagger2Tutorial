package me.tittojose.dagger2tutorial.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import me.tittojose.dagger2tutorial.Dagger2TutorialApp;
import me.tittojose.dagger2tutorial.R;
import me.tittojose.dagger2tutorial.model.MoviesList;
import me.tittojose.dagger2tutorial.network.MovieAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MovieAPIService movieAPIService;
    Call<MoviesList> movieListRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAPIService = Dagger2TutorialApp.get(this).getMovieAPIService();

        movieListRequest = movieAPIService.getPopularMovies();

        movieListRequest.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                Toast.makeText(MainActivity.this, moviesList.results.size()+"", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (movieListRequest != null) {
            movieListRequest.cancel();
        }
    }
}
