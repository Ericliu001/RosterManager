package com.example.presentation.presenter;

import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.ShiftRepository;
import com.example.presentation.view.ItemListActivityView;

/**
 * Created by ericliu on 14/3/17.
 */

public class ItemListActivityPresenter extends BasePresenter<ItemListActivityView>{

    public ItemListActivityPresenter(BusinessInfoRepository businessInfoRepository
                                    , ShiftRepository shiftRepository) {
    }

    public void onViewCreated(final boolean isConfigurationChange) {

    }

    public void doSomething() {
        view.dummyCall();
    }
}
