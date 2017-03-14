package com.example.data.repository;

import com.example.data.entity.FinishedShift;
import com.example.data.entity.Shift;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by ericliu on 10/3/17.
 */

public interface ShiftRepository {

    Observable<List<FinishedShift>> getPreviousShifts();

    Completable startShift(Shift shift);

    Completable endShift(Shift shift);

}
