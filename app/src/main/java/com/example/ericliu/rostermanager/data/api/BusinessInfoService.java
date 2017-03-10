package com.example.ericliu.rostermanager.data.api;

import com.example.data.entity.BusinessInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static com.example.ericliu.rostermanager.dagger.ApiModule.AUTH_SHA1;

/**
 * Created by ericliu on 10/3/17.
 */

public interface BusinessInfoService {
    @Headers("Authorization:Deputy " + AUTH_SHA1)
    @GET("business") //
    Observable<BusinessInfo> businessInfo();
}
