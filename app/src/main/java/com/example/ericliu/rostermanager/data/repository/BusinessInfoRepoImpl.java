package com.example.ericliu.rostermanager.data.repository;

import com.example.data.entity.BusinessInfo;
import com.example.data.repository.BusinessInfoRepository;
import com.example.ericliu.rostermanager.data.api.BusinessInfoService;

import io.reactivex.Observable;

/**
 * Created by ericliu on 10/3/17.
 */

public class BusinessInfoRepoImpl implements BusinessInfoRepository {

    private BusinessInfoService businessInfoService;

    public BusinessInfoRepoImpl(BusinessInfoService businessInfoService) {
        this.businessInfoService = businessInfoService;
    }

    @Override
    public Observable<BusinessInfo> getBusinessInfo() {
        return businessInfoService.businessInfo();
    }
}
