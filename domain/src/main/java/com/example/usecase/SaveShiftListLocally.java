package com.example.usecase;

import com.example.data.entity.Shift;
import com.example.data.repository.LocalShiftRepository;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by ericliu on 10/3/17.
 */

public class SaveShiftListLocally implements UseCase {

    private final LocalShiftRepository localShiftRepository;
    private List<Shift> shifts;

    public SaveShiftListLocally(LocalShiftRepository localShiftRepository, List<Shift> shifts) {
        this.localShiftRepository = localShiftRepository;
        this.shifts = shifts;
    }

    @Override
    public Completable execute() {
        return localShiftRepository.saveShiftList(shifts);
    }
}
