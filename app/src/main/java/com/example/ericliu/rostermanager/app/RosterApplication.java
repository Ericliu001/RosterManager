package com.example.ericliu.rostermanager.app;

import android.app.Application;

import com.example.ericliu.rostermanager.dagger.ApiModule;
import com.example.ericliu.rostermanager.dagger.ApplicationComponent;
import com.example.ericliu.rostermanager.dagger.ApplicationModule;
import com.example.ericliu.rostermanager.dagger.DaggerApplicationComponent;
import com.example.ericliu.rostermanager.dagger.RepoModule;

/**
 * Created by ericliu on 10/3/17.
 */

public class RosterApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .repoModule(new RepoModule())
                .build();
    }

    public ApplicationComponent component() {
        return applicationComponent;
    }
}
