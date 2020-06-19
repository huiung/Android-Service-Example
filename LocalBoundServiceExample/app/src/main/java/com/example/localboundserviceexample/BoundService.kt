package com.example.localboundserviceexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class BoundService : Service() {

    val myBinder: IBinder = MyLocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    fun getCurrentTime() : String {

        val dateformat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.KOREA)
        return dateformat.format(Date())
    }

    inner class MyLocalBinder: Binder() {
        fun getService() : BoundService = this@BoundService
    }

}
