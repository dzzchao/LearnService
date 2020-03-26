package com.dzzchao.serivcedemo

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by zhangchao on 2018/2/26.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        Timber.d("Learn Service")
    }
}