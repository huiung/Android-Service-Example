package com.example.localboundserviceexample

    import android.content.ComponentName
    import android.content.Context
    import android.content.Intent
    import android.content.ServiceConnection
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.os.IBinder
    import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

        private lateinit var myService : BoundService
        private var isBound = false

        private val connection = object : ServiceConnection {

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                val binder = p1 as BoundService.MyLocalBinder
                myService = binder.getService()
                isBound = true
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                isBound = false
            }
        }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            button.setOnClickListener {
                if(isBound)
                    textView.setText(myService.getCurrentTime())
            }
        }

        override fun onStart() {
            super.onStart()
            Intent(this, BoundService::class.java).also {intent->
                bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
        }

        override fun onStop() {
            super.onStop()
            unbindService(connection)
            isBound = false
        }
}
