package com.example.presentation.presenter;

/**
 * Created by ericliu on 14/3/17.
 */

public abstract class BasePresenter<V> {

    protected V view;

    public void onCreate(final V v) {this.view = v;}

    public abstract void onViewCreated(boolean isConfigurationChange);

    public void onResume(){}

    public void onPause(){}

    public void onViewDestroyed(){
        view = (V) DummyViewGenerator.createDummyInstance(this);
    }

}
