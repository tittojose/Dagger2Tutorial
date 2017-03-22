package me.tittojose.dagger2tutorial;


import android.app.Activity;
import android.app.Application;

import me.tittojose.dagger2tutorial.network.MovieAPIService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dagger2TutorialApp extends Application {

    MovieAPIService movieAPIService;

    public static Dagger2TutorialApp get(Activity activity) {
        return (Dagger2TutorialApp) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(gsonConverterFactory)
                .build();

        movieAPIService = retrofit.create(MovieAPIService.class);

    }

    public MovieAPIService getMovieAPIService() {
        return movieAPIService;
    }
}
