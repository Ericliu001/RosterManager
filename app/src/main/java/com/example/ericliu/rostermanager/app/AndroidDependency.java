package com.example.ericliu.rostermanager.app;

import com.example.presentation.AndroidWapper;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ericliu on 14/3/17.
 */

public class AndroidDependency implements AndroidWapper {
    @Override
    public Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
