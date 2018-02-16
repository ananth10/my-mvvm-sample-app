package com.ananth.rxandroidwithretrofit;


import android.app.Activity;
import android.app.Application;

import com.ananth.rxandroidwithretrofit.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Babu on 2/12/2018.
 */

public class MyApplication extends Application implements HasActivityInjector {


//    @Override
//    protected AndroidInjector<? extends MyApplication> applicationInjector() {
//        return DaggerAppComponent.builder().create(this);
//    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
