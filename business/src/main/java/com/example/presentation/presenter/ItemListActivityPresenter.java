package com.example.presentation.presenter;

import com.example.data.entity.BusinessInfo;
import com.example.data.entity.FinishedShift;
import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.ShiftRepository;
import com.example.presentation.view.ItemListActivityView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by ericliu on 14/3/17.
 */

public class ItemListActivityPresenter extends BasePresenter<ItemListActivityView>{

    private final BusinessInfoRepository businessInfoRepository;
    private final ShiftRepository shiftRepository;
    private DisposableObserver<BusinessInfo> businessInfoDisposableObserver;
    private DisposableObserver<List<FinishedShift>> shiftObserver;

    public ItemListActivityPresenter(BusinessInfoRepository businessInfoRepository
                                    , ShiftRepository shiftRepository) {
        this.businessInfoRepository = businessInfoRepository;
        this.shiftRepository = shiftRepository;
    }

    public void onViewCreated(final boolean isConfigurationChange) {
        businessInfoDisposableObserver = new DisposableObserver<BusinessInfo>() {
            @Override
            public void onNext(final BusinessInfo businessInfo) {
                view.showBusinessInfo();
            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        businessInfoRepository.getBusinessInfo().subscribe(businessInfoDisposableObserver);

        shiftObserver = new DisposableObserver<List<FinishedShift>>() {
            @Override
            public void onNext(final List<FinishedShift> finishedShifts) {

            }

            @Override
            public void onError(final   Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        shiftRepository.getPreviousShifts().subscribe(shiftObserver);
    }


    @Override
    public void onDestroy() {
        businessInfoDisposableObserver.dispose();
        shiftObserver.dispose();
    }

    public void doSomething() {
        view.showBusinessInfo();
    }
}
