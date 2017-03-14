package com.example.presentation.presenter;

import com.example.data.entity.BusinessInfo;
import com.example.data.entity.FinishedShift;
import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.ShiftRepository;
import com.example.presentation.AndroidWapper;
import com.example.presentation.view.ItemListActivityView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ericliu on 14/3/17.
 */

public class ItemListActivityPresenter extends BasePresenter<ItemListActivityView> {

    private AndroidWapper androidWapper;
    private final BusinessInfoRepository businessInfoRepository;
    private final ShiftRepository shiftRepository;
    private DisposableObserver<BusinessInfo> businessInfoDisposableObserver;
    private DisposableObserver<List<FinishedShift>> shiftObserver;

    public ItemListActivityPresenter(AndroidWapper androidWapper, BusinessInfoRepository businessInfoRepository
            , ShiftRepository shiftRepository) {
        this.androidWapper = androidWapper;
        this.businessInfoRepository = businessInfoRepository;
        this.shiftRepository = shiftRepository;
    }

    @Override
    public void onViewCreated(final boolean isConfigurationChange) {
    }

    @Override
    public void onResume() {
        businessInfoDisposableObserver = new DisposableObserver<BusinessInfo>() {
            @Override
            public void onNext(final BusinessInfo businessInfo) {
                if (businessInfo.name != null) {
                    view.showBusinessName(businessInfo.name);
                }
                if (businessInfo.logo != null) {
                    view.showBusinessLogo(businessInfo.logo);
                }
            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        businessInfoRepository.getBusinessInfo().observeOn(androidWapper.getMainThreadScheduler()).subscribeOn(Schedulers.io()).subscribe(businessInfoDisposableObserver);

        shiftObserver = new DisposableObserver<List<FinishedShift>>() {
            @Override
            public void onNext(final List<FinishedShift> finishedShifts) {

            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        shiftRepository.getPreviousShifts().observeOn(androidWapper.getMainThreadScheduler()).subscribeOn(Schedulers.io()).subscribe(shiftObserver);
    }


    @Override
    public void onDestroy() {
        businessInfoDisposableObserver.dispose();
        shiftObserver.dispose();
    }

}
