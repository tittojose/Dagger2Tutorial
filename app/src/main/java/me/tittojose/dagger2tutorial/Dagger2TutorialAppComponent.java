package me.tittojose.dagger2tutorial;

import com.squareup.picasso.Picasso;

import dagger.Component;
import me.tittojose.dagger2tutorial.network.MovieAPIService;

@Dagger2ApplicationScope
@Component(modules = {MoviesAPIServiceModule.class, PicassoModule.class})
public interface Dagger2TutorialAppComponent {

    Picasso getPicasso();

    MovieAPIService getMovieApiService();

}