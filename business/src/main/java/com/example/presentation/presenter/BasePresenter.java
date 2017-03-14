package com.example.presentation.presenter;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by ericliu on 14/3/17.
 */

public abstract class BasePresenter<V> {

    protected V view;
    protected List<Disposable> disposableList = new ArrayList<>();

    public void onCreate(final V v) {this.view = v;}

    public abstract void onViewCreated(boolean isConfigurationChange);

    public void onResume(){}

    public void onPause(){}

    public void onViewDestroyed(){
        view = (V) DummyViewGenerator.createDummyInstance(this);
    }

    public void onDestroy(){
        for (final Disposable disposable : disposableList) {
            disposable.dispose();
        }
    }

}
