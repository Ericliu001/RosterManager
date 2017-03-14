package com.example.data.repository;

import com.example.data.entity.BusinessInfo;

import io.reactivex.Observable;

/**
 * Created by ericliu on 12/3/17.
 */

public class MockedBusinessInfoRepository implements BusinessInfoRepository {
    @Override
    public Observable<BusinessInfo> getBusinessInfo() {
        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.logo = "adsfa";
        businessInfo.name = "Deputy";
        return Observable.just(businessInfo);
    }
}
