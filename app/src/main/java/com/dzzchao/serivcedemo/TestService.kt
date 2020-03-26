package com.dzzchao.serivcedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.dzzchao.serivcedemo.TestService
import timber.log.Timber
import java.util.*

/**
 *
 * @author zhangchao
 * @date 2018/2/26
 */
class TestService : Service() {
    internal inner class MyBinder : Binder() {
        val service: TestService
            get() = this@TestService
    }

    //通过binder实现调用者client与Service之间的通信
    private val binder = MyBinder()
    private val generator = Random()
    override fun onCreate() {
        Timber.d("onCreate Thread: %s", Thread.currentThread().name)
        super.onCreate()
    }

    override fun onStart(intent: Intent, startId: Int) {
        Timber.d("onStart")
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand Thread: %s %d %d", Thread.currentThread().name, flags, startId)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Timber.d("onBind Thread: %s", Thread.currentThread().name)
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Timber.d("onUnbind Thread: %s", Thread.currentThread().name)
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent) {
        Timber.d("onRebind Thread: %s", Thread.currentThread().name)
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Timber.d("onDestroy Thread: %s", Thread.currentThread().name)
        super.onDestroy()
    }

    //getRandomNumber是Service暴露出去供client调用的公共方法
    val randomNumber: Int
        get() = generator.nextInt()
}