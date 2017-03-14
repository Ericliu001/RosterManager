package com.example.presentation.presenter;

import com.example.AndroidWapper;
import com.example.data.entity.BusinessInfo;
import com.example.data.entity.FinishedShift;
import com.example.data.entity.Shift;
import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.LocationRepository;
import com.example.data.repository.ShiftRepository;
import com.example.presentation.view.ItemListActivityView;
import com.example.util.ShiftFactory;

import java.util.List;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ericliu on 14/3/17.
 */

public class ItemListActivityPresenter extends BasePresenter<ItemListActivityView> {

    private final AndroidWapper androidWapper;
    private final BusinessInfoRepository businessInfoRepository;
    private final ShiftRepository shiftRepository;
    private final LocationRepository locationRepository;

    private DisposableCompletableObserver startShiftCompletable;
    private Shift startedShift;

    public ItemListActivityPresenter(AndroidWapper androidWapper
            , BusinessInfoRepository businessInfoRepository
            , ShiftRepository shiftRepository
            , LocationRepository locationRepository) {
        this.androidWapper = androidWapper;
        this.businessInfoRepository = businessInfoRepository;
        this.shiftRepository = shiftRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void onViewCreated(final boolean isConfigurationChange) {
    }

    @Override
    public void onResume() {
        super.onResume();
        final DisposableObserver<BusinessInfo> businessInfoDisposableObserver = new DisposableObserver<BusinessInfo>() {
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
        disposableList.add(businessInfoDisposableObserver);

        businessInfoRepository.getBusinessInfo()
                .observeOn(androidWapper.getMainThreadScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(businessInfoDisposableObserver);

        final DisposableObserver<List<FinishedShift>> finishedShiftObserver = new DisposableObserver<List<FinishedShift>>() {
            @Override
            public void onNext(final List<FinishedShift> finishedShifts) {
                view.showFinishedShiftsList(finishedShifts);
            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        disposableList.add(finishedShiftObserver);

        shiftRepository.getPreviousShifts()
                .observeOn(androidWapper.getMainThreadScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(finishedShiftObserver);
    }



    public void onStartShiftButtonClick() {


        final DisposableObserver<Shift> createShiftObserver = new DisposableObserver<Shift>() {
            @Override
            public void onNext(final Shift shift) {
                startedShift = shift;
                shiftRepository.startShift(startedShift)
                        .observeOn(androidWapper.getMainThreadScheduler())
                        .subscribeOn(Schedulers.io())
                        .subscribe(startShiftCompletable);
            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        disposableList.add(createShiftObserver);

        startShiftCompletable = new DisposableCompletableObserver() {

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(final Throwable e) {

            }
        };

        disposableList.add(startShiftCompletable);

        view.showStartedShiftLayoutWithProgressBar();

        ShiftFactory.newInstance().createShift(locationRepository)
                .subscribeOn(Schedulers.io())
                .observeOn(androidWapper.getMainThreadScheduler())
                .subscribe(createShiftObserver)

        ;

    }
}
