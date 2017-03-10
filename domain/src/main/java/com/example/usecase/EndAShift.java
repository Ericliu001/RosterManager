package com.example.usecase;

import com.example.data.entity.Shift;
import com.example.data.repository.ShiftRepository;

import io.reactivex.Completable;

/**
 * Created by ericliu on 10/3/17.
 */

public class EndAShift implements UseCase {

    private final ShiftRepository shiftRepository;
    private final Shift shift;

    public EndAShift(ShiftRepository shiftRepository, Shift shift) {
        this.shiftRepository = shiftRepository;
        this.shift = shift;
    }

    @Override
    public Completable execute() {
        return shiftRepository.endShift(shift);
    }
}
