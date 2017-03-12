package com.example.ericliu.rostermanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ericliu.rostermanager.app.RosterApplication;

/**
 * Created by ericliu on 10/3/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RosterApplication) getApplication()).getApplicationComponent().inject(this);
    }
}
