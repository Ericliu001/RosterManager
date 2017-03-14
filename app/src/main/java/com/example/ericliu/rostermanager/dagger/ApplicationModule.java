package com.example.ericliu.rostermanager.dagger;

import com.example.ericliu.rostermanager.app.AndroidDependency;
import com.example.presentation.AndroidWapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ericliu on 14/3/17.
 */
@Module
public class ApplicationModule {

    @Provides @Singleton
    public AndroidWapper provideAndroidWrapWapper() {
        return new AndroidDependency();
    }
}
