package com.example.data.repository;

import io.reactivex.Observable;

/**
 * Created by ericliu on 14/3/17.
 */

public interface LocationRepository {

    Observable<Location> getLocation();

    class Location {
        public String latitude;
        public String longitude;
    }
}
