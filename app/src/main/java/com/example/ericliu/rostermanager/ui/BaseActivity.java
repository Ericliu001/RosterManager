package com.example.ericliu.rostermanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ericliu.rostermanager.app.RosterApplication;
import com.example.ericliu.rostermanager.dagger.ActivityModule;
import com.example.ericliu.rostermanager.dagger.BaseActivityComponent;
import com.example.ericliu.rostermanager.dagger.DaggerBaseActivityComponent;
import com.example.ericliu.rostermanager.dagger.PresenterModule;

/**
 * Created by ericliu on 10/3/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BaseActivityComponent component;

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
    }

    protected abstract void inject(BaseActivityComponent component);

}
