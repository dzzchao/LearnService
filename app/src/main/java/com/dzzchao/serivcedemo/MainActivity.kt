package com.dzzchao.serivcedemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dzzchao.serivcedemo.TestService.MyBinder
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main), View.OnClickListener {

    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn_start.setOnClickListener(this)
        btn_stop.setOnClickListener(this)
        btn_bind.setOnClickListener(this)
        btn_unbind.setOnClickListener(this)
        btn_second.setOnClickListener(this)
        btn_intentservice.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start -> {
                Toast.makeText(this, "hha", Toast.LENGTH_SHORT).show()
                startService(Intent(this, TestService::class.java))
            }
            R.id.btn_stop -> stopService(Intent(this, TestService::class.java))
            R.id.btn_bind -> {
                val intent = Intent(this, TestService::class.java)
                bindService(intent, conn, BIND_AUTO_CREATE)
            }
            R.id.btn_unbind -> {
                if (isBound) {
                    unbindService(conn);
                    isBound = false;
                }
            }
            R.id.btn_second -> {
                startActivity(Intent(this, SecondActivity::class.java))
            }
            R.id.btn_intentservice -> {
                startService(Intent(this, MyIntentService::class.java))
            }
        }
    }

    private val conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            Timber.d("onServiceConnected")
            isBound = true
            val myBinder = binder as MyBinder
            val service = myBinder.service
            val randomNumber = service.randomNumber
            Timber.d("randomNum: %d", randomNumber)
        }

        /**
         * 此方法在主动 unbind 之后，并不会被调用，通常在 Service 所在进程被迫终止时才会调用
         * @param name
         */
        override fun onServiceDisconnected(name: ComponentName) {
            Timber.d("onServiceDisconnected")
            isBound = false
        }
    }
}