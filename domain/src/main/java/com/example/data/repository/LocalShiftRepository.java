package com.example.data.repository;

import com.example.data.entity.Shift;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by ericliu on 10/3/17.
 */

public interface LocalShiftRepository {
    Completable saveShift(Shift shift);
    Completable saveShiftList(List<Shift> shiftList);
}
