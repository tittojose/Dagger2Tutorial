package me.tittojose.dagger2tutorial;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;


    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Dagger2ApplicationScope
    public Context getContext() {
        return this.context;
    }
}
