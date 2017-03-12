package com.example.ericliu.rostermanager.dagger;

import com.example.ericliu.rostermanager.data.api.BusinessInfoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericliu on 10/3/17.
 */

@Module
public final class ApiModule {
    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://apjoqdqpi3.execute-api.us-west-2.amazonaws.com/dmc/");
    public static final String AUTH_SHA1 = "96f164ad4d9b2b0dacf8ebee2bb1eeb3aa69adf1";

    @Provides
    @Singleton HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }


    @Provides @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder() //
                .baseUrl(PRODUCTION_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides @Singleton
    BusinessInfoService provideBusinessInfoService(Retrofit retrofit) {
        return retrofit.create(BusinessInfoService.class);
    }

}
