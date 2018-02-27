package com.dzzchao.serivcedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private boolean isBound = false;

    @OnClick(R.id.btn_start)
    void start() {
        startService(new Intent(this, TestService.class));
    }

    @OnClick(R.id.btn_stop)
    void stop() {
        stopService(new Intent(this, TestService.class));
    }

    @OnClick(R.id.btn_bind)
    void bind() {
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.btn_unbind)
    void unbind() {
        if (isBound) {
            unbindService(conn);
            isBound = false;
        }
    }

    @OnClick(R.id.btn_second)
    void second() {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @OnClick(R.id.btn_intentservice)
    void intentService() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Timber.d("onServiceConnected");
            isBound = true;
            TestService.MyBinder myBinder = (TestService.MyBinder) binder;
            TestService service = myBinder.getService();
            int randomNumber = service.getRandomNumber();
            Timber.d("randomNum: %s", randomNumber);
        }

        /**
         * 此方法在主动 unbind 之后，并不会被调用，通常在 Service 所在进程被迫终止时才会调用
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Timber.d("onServiceDisconnected");
            isBound = false;
        }
    };
}