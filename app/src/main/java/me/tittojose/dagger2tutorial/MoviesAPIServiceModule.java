package me.tittojose.dagger2tutorial;

import dagger.Module;
import dagger.Provides;
import me.tittojose.dagger2tutorial.network.MovieAPIService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class MoviesAPIServiceModule {

    @Provides
    public MovieAPIService movieAPIService(Retrofit retrofit) {

        return retrofit.create(MovieAPIService.class);
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }


}
