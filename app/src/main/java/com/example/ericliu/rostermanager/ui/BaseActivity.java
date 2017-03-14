package com.example.ericliu.rostermanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ericliu.rostermanager.app.RosterApplication;
import com.example.ericliu.rostermanager.dagger.ActivityModule;
import com.example.ericliu.rostermanager.dagger.BaseActivityComponent;
import com.example.ericliu.rostermanager.dagger.DaggerBaseActivityComponent;
import com.example.ericliu.rostermanager.dagger.PresenterModule;
import com.example.presentation.presenter.BasePresenter;

/**
 * Created by ericliu on 10/3/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BaseActivityComponent component;
    private BasePresenter basePresenter;

    BaseActivityComponent component() {
        if (component == null) {
            component = DaggerBaseActivityComponent.builder()
                    .applicationComponent(((RosterApplication) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .presenterModule(new PresenterModule())
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().inject(this);
        inject(component());
        basePresenter = attachPresenter();
        if (basePresenter != null) {
            basePresenter.onCreate(this);
            basePresenter.onViewCreated(savedInstanceState != null);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (basePresenter != null) {
            basePresenter.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (basePresenter != null) {
            basePresenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        if (basePresenter != null) {
            basePresenter.onViewDestroyed();
            basePresenter.onDestroy();
        }
        super.onDestroy();
    }

    protected abstract void inject(BaseActivityComponent component);

    protected abstract BasePresenter attachPresenter();

}
