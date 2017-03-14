package com.example.data.repository;

import com.example.data.entity.BusinessInfo;

import io.reactivex.Observable;

/**
 * Created by ericliu on 10/3/17.
 */

public interface BusinessInfoRepository {
    Observable<BusinessInfo> getBusinessInfo();
}
