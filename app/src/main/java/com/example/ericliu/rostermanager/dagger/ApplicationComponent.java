package com.example.ericliu.rostermanager.dagger;

import com.example.ericliu.rostermanager.data.api.BusinessInfoService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ericliu on 10/3/17.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApiModule.class)
public interface ApplicationComponent {
    BusinessInfoService businessInfoService();
}
