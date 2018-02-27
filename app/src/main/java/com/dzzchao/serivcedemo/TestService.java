package com.dzzchao.serivcedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

import timber.log.Timber;

/**
 * @author zhangchao
 * @date 2018/2/26
 */
public class TestService extends Service {

    public class MyBinder extends Binder {
        public TestService getService() {
            return TestService.this;
        }
    }

    //通过binder实现调用者client与Service之间的通信
    private MyBinder binder = new MyBinder();

    private final Random generator = new Random();

    @Override
    public void onCreate() {
        Timber.d("onCreate Thread: %s", Thread.currentThread().getName());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("onStartCommand Thread: %s %d %d", Thread.currentThread().getName(), flags, startId);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Timber.d("onBind Thread: %s", Thread.currentThread().getName());
        return binder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Timber.d("onUnbind Thread: %s", Thread.currentThread().getName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Timber.d("onRebind Thread: %s", Thread.currentThread().getName());
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Timber.d("onDestroy Thread: %s", Thread.currentThread().getName());
        super.onDestroy();
    }

    //getRandomNumber是Service暴露出去供client调用的公共方法
    public int getRandomNumber() {
        return generator.nextInt();
    }
}
