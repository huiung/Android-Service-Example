package com.example.remoteboundserviceexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    private  var myService : Messenger? = null
    private var isBound = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            myService = Messenger(p1)
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            myService = null
            isBound = false
        }
    }

    fun sendMessage(view: View) {
        if(!isBound) return
        val msg = Message.obtain()
        val bundle = Bundle()
        bundle.putString("MyString", "This is Message!")
        msg.data = bundle

        try {
            myService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyRemoteService::class.java).also { intent->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if(isBound) {
            unbindService(connection)
            isBound = false
        }
    }

}
