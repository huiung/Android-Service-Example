package com.example.startedservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock.sleep
import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import java.lang.Exception

class MyService : Service() {

    override fun onCreate() {
        Log.d("TAG", "Service Create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("TAG", "Service Start")

        val r = Runnable() {
            val time = System.currentTimeMillis() + 5*1000

            while(System.currentTimeMillis() < time) {
                synchronized(this) {
                    try {
                        Thread.sleep(time - System.currentTimeMillis())
                    } catch (e: Exception) {

                    }
                }
            }
            stopSelf(startId)
        }

        val t = Thread(r)
        t.start()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d("TAG", "Service Destroy")
        super.onDestroy()
    }

}
