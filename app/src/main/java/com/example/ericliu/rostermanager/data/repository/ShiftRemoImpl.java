package com.example.ericliu.rostermanager.data.repository;

import com.example.data.entity.PreviousShift;
import com.example.data.entity.Shift;
import com.example.data.repository.ShiftRepository;
import com.example.ericliu.rostermanager.data.api.ShiftService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by ericliu on 14/3/17.
 */

public class ShiftRemoImpl implements ShiftRepository {

    private ShiftService shiftService;

    public ShiftRemoImpl(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @Override
    public Observable<List<PreviousShift>> getPreviousShifts() {
        return shiftService.getPreviousShifts();
    }

    @Override
    public Completable startShift(final Shift shift) {
        return shiftService.startAShift(shift);
    }

    @Override
    public Completable endShift(final Shift shift) {
        return shiftService.endAShift(shift);
    }
}
