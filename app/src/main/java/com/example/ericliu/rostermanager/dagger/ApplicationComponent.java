package com.example.ericliu.rostermanager.dagger;

import com.example.ericliu.rostermanager.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ericliu on 10/3/17.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApiModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity activity);
}
