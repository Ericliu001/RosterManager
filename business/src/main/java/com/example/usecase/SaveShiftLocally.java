package com.example.usecase;

import com.example.data.entity.Shift;
import com.example.data.repository.LocalShiftRepository;

import io.reactivex.Completable;

/**
 * Created by ericliu on 10/3/17.
 */

public class SaveShiftLocally implements UseCase {

    private final LocalShiftRepository localShiftRepository;
    private Shift shift;

    public SaveShiftLocally(LocalShiftRepository localShiftRepository, Shift shift) {
        this.localShiftRepository = localShiftRepository;
        this.shift = shift;
    }

    @Override
    public Completable execute() {
        return localShiftRepository.saveShift(shift);
    }
}
