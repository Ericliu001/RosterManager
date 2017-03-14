package com.example.ericliu.rostermanager.data.api;

import com.example.data.entity.PreviousShift;
import com.example.data.entity.Shift;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.example.ericliu.rostermanager.dagger.ApiModule.AUTH_SHA1;

/**
 * Created by ericliu on 14/3/17.
 */

public interface ShiftService {
    @Headers("Authorization:Deputy " + AUTH_SHA1)
    @POST("shift/start") //
    Completable startAShift(@Body Shift shift);

    @Headers("Authorization:Deputy " + AUTH_SHA1)
    @POST("shift/end") //
    Completable endAShift(@Body Shift shift);

    @Headers("Authorization:Deputy " + AUTH_SHA1)
    @GET("shifts") //
    Observable<List<PreviousShift>> getPreviousShifts();
}
