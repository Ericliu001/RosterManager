package com.example.ericliu.rostermanager.dagger;

import com.example.data.repository.BusinessInfoRepository;
import com.example.data.repository.ShiftRepository;
import com.example.ericliu.rostermanager.data.api.BusinessInfoService;
import com.example.ericliu.rostermanager.data.api.ShiftService;
import com.example.presentation.AndroidWapper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ericliu on 10/3/17.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, ApiModule.class, RepoModule.class})
public interface ApplicationComponent {
    BusinessInfoService businessInfoService();
    ShiftService shiftService();

    BusinessInfoRepository businessInfoRepository();
    ShiftRepository shiftRepository();

    AndroidWapper androidWapper();
}
