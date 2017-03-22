package me.tittojose.dagger2tutorial.network;


import me.tittojose.dagger2tutorial.model.MoviesList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPIService {
    @GET("discover/movie?api_key=c42a19b7c0875d3c364ce0fe2dc6b7a0&language=en-US&sort_by=popularity.desc")
    Call<MoviesList> getPopularMovies();

}
