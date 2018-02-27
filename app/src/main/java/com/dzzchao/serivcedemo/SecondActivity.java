package com.dzzchao.serivcedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SecondActivity extends AppCompatActivity {

    private boolean isBound = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
