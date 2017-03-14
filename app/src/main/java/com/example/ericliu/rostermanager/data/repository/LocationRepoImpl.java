package com.example.ericliu.rostermanager.data.repository;

import android.app.Application;

import com.example.data.repository.LocationRepository;
import com.example.ericliu.rostermanager.util.LastLocationFinder;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Created by ericliu on 14/3/17.
 */

public class LocationRepoImpl implements LocationRepository {

    private Application application;

    public LocationRepoImpl(Application application) {
        this.application = application;
    }

    @Override
    public Observable<Location> getLocation() {
        return Observable.fromCallable(new Callable<Location>() {
            @Override
            public Location call() throws Exception {
                android.location.Location lastBestLocation = LastLocationFinder.getLastBestLocation(application);
                final Location location = new Location();
                location.latitude = String.valueOf(lastBestLocation.getLatitude());
                location.longitude = String.valueOf(lastBestLocation.getLongitude());
                return location;
            }
        });
    }
}
