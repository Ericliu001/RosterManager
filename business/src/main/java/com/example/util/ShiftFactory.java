package com.example.util;

import com.example.data.entity.Shift;
import com.example.data.repository.LocationRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by ericliu on 14/3/17.
 */

public class ShiftFactory {
    public static ShiftFactory newInstance() {
        return new ShiftFactory();
    }

    public Observable<Shift> createShift(LocationRepository locationRepository) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        Date date = new Date();
        String dateStr = df.format(date);

        final Shift shift = new Shift();
        shift.time = dateStr;
        return locationRepository.getLocation()
                .flatMap(new Function<LocationRepository.Location, ObservableSource<Shift>>() {
                    @Override
                    public ObservableSource<Shift> apply(final LocationRepository.Location location) throws Exception {
                        shift.latitude = location.latitude;
                        shift.longitude = location.longitude;
                        return Observable.just(shift);
                    }
                });
    }
}
