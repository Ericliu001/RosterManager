package com.example.ericliu.rostermanager.data.repository;

import com.example.data.entity.BusinessInfo;
import com.example.data.repository.BusinessInfoRepository;

import io.reactivex.Observable;

/**
 * Created by ericliu on 10/3/17.
 */

public class BusinessInfoRepo implements BusinessInfoRepository {
    @Override
    public Observable<BusinessInfo> getBusinessInfo() {
        return null;
    }
}
