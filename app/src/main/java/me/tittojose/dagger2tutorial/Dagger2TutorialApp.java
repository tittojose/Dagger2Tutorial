package me.tittojose.dagger2tutorial;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import me.tittojose.dagger2tutorial.network.MovieAPIService;
import timber.log.Timber;

public class Dagger2TutorialApp extends Application {

    MovieAPIService movieAPIService;
    Picasso picasso;

    public static Dagger2TutorialApp get(Activity activity) {
        return (Dagger2TutorialApp) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this;
        Timber.plant(new Timber.DebugTree());

        Dagger2TutorialAppComponent component = DaggerDagger2TutorialAppComponent.builder()
                .contextModule(new ContextModule(context))
                .build();

        movieAPIService = component.getMovieApiService();
        picasso = component.getPicasso();
    }

    public MovieAPIService getMovieAPIService() {
        return movieAPIService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
