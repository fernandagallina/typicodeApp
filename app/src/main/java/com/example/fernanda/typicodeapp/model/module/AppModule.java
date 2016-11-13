package com.example.fernanda.typicodeapp.model.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernanda on 12/11/2016.
 *
 * It'll provide context to other modules.
 * AppModule will be instantiated when the Application starts.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplicatiom() {
        return mApplication;
    }
}
