package com.example.remoteboundserviceexample

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

class MyRemoteService : Service() {

    private lateinit var mMessenger: Messenger

    internal class IncomingHandler(context: Context, val applicationContext: Context = context.applicationContext) : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val data = msg.data
            val str = data.getString("MyString")
            Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }
}
