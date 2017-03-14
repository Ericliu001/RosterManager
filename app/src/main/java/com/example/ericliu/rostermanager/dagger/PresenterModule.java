package com.example.ericliu.rostermanager.dagger;

import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.ShiftRepository;
import com.example.presentation.presenter.ItemListActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ericliu on 14/3/17.
 */

@Module
public class PresenterModule {

    @Provides @PerActivity
    public ItemListActivityPresenter provideItemListActivityPresenter(BusinessInfoRepository businessInfoRepository
            , ShiftRepository shiftRepository) {
        return new ItemListActivityPresenter(businessInfoRepository, shiftRepository);
    }
}
