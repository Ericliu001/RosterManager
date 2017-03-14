package com.example.ericliu.rostermanager.dagger;

import android.app.Application;

import com.example.AndroidWapper;
import com.example.ericliu.rostermanager.app.AndroidDependency;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ericliu on 14/3/17.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(final Application application) {
        this.application = application;
    }

    @Provides @Singleton
    public Application provideApplication(){
        return application;
    }

    @Provides @Singleton
    public AndroidWapper provideAndroidWrapWapper() {
        return new AndroidDependency();
    }
}
