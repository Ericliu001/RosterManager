package com.example.usecase;

import com.example.data.entity.PreviousShift;
import com.example.data.repository.ShiftRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ericliu on 10/3/17.
 */

public class GetListOfPreviousShift implements UseCase {

    private ShiftRepository shiftRepository;

    public GetListOfPreviousShift(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public Observable<List<PreviousShift>> execute() {
        return shiftRepository.getPreviousShifts();
    }
}
