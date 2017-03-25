package me.tittojose.dagger2tutorial;


import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @Dagger2ApplicationScope
    public OkHttpClient getOkHttpClient(Interceptor logger, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(logger)
                .cache(cache)
                .build();
    }

    @Provides
    @Dagger2ApplicationScope
    public Interceptor getInterceptor() {

        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });
    }

    @Provides
    @Dagger2ApplicationScope
    public Cache getCache(File cacheDir) {
        return new Cache(cacheDir, 10 * 1000 * 1000);
    }

    @Provides
    @Dagger2ApplicationScope
    public File getFile(Context context) {
        File okhttpCache = new File(context.getCacheDir(), "okhttp_cache");
        okhttpCache.mkdirs();
        return okhttpCache;
    }


}
