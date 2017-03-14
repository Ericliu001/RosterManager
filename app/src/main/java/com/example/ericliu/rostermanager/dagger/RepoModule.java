package com.example.ericliu.rostermanager.dagger;

import android.app.Application;

import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.LocationRepository;
import com.example.data.repository.ShiftRepository;
import com.example.ericliu.rostermanager.data.api.BusinessInfoService;
import com.example.ericliu.rostermanager.data.api.ShiftService;
import com.example.ericliu.rostermanager.data.repository.BusinessInfoRepoImpl;
import com.example.ericliu.rostermanager.data.repository.LocationRepoImpl;
import com.example.ericliu.rostermanager.data.repository.ShiftRepoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ericliu on 14/3/17.
 */
@Module
public class RepoModule {

    @Provides @Singleton
    public BusinessInfoRepository provideBusinessInfoRepository(BusinessInfoService businessInfoService) {
        return new BusinessInfoRepoImpl(businessInfoService);
    }

    @Provides @Singleton
    public ShiftRepository provideShiftRepository(ShiftService shiftService) {
        return new ShiftRepoImpl(shiftService);
    }

    @Provides @Singleton
    public LocationRepository provideLocationRepository(Application application){
        return new LocationRepoImpl(application);
    }


}
