package com.example.presentation.view;

import com.example.data.entity.FinishedShift;

import java.util.List;

/**
 * Created by ericliu on 14/3/17.
 */

public interface ItemListActivityView {
    void showBusinessName(String name);

    void showBusinessLogo(String logoUrl);

    void showFinishedShiftsList(List<FinishedShift> finishedShifts);

    void showStartedShiftLayoutWithProgressBar();
}
