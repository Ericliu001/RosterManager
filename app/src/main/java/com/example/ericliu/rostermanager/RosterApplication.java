package com.example.ericliu.rostermanager;

import android.app.Application;

import com.example.ericliu.rostermanager.dagger.ApiModule;
import com.example.ericliu.rostermanager.dagger.ApplicationComponent;
import com.example.ericliu.rostermanager.dagger.DaggerApplicationComponent;

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
                .apiModule(new ApiModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
