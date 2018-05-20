package com.dzzchao.serivcedemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.Nullable;

import timber.log.Timber;

/**
 * @author zhangchao
 * @date 2018/2/28
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        boolean isMainThread = Thread.currentThread() == Looper.getMainLooper().getThread();
        Timber.d("Is main thread ? %s", isMainThread);

        try {
            Thread.sleep(5000);
            Timber.d("downaload Over");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
