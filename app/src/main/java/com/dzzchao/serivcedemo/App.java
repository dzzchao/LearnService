package com.dzzchao.serivcedemo;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by zhangchao on 2018/2/26.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("Learn Service");


    }
}
