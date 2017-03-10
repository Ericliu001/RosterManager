package com.example.usecase;

import com.example.data.entity.BusinessInfo;
import com.example.data.repository.BusinessInfoRepository;

import io.reactivex.Observable;

/**
 * Created by ericliu on 10/3/17.
 */

public class GetBusinessInfo implements UseCase {


    private BusinessInfoRepository businessInfoRepository;

    public GetBusinessInfo(BusinessInfoRepository businessInfoRepository) {
        this.businessInfoRepository = businessInfoRepository;
    }

    @Override
    public Observable<BusinessInfo> execute() {
        return businessInfoRepository.getBusinessInfo();
    }
}
