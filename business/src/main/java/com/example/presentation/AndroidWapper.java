package com.example.presentation;

import io.reactivex.Scheduler;

/**
 * Created by ericliu on 14/3/17.
 */

public interface AndroidWapper {
    Scheduler getMainThreadScheduler();
}
