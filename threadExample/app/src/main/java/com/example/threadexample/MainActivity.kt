package com.example.threadexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val handler = object: Handler() {
        override fun handleMessage(msg: Message) { //스레드가 핸들러에게 메시지를 보낼 때 호출됨
            val bundle = msg.data
            val string = bundle.getString("str")
            textview.text = string
            super.handleMessage(msg)
        }


    }


    public fun btnClick(view: View) {

        val runnable = Runnable { //run만 override 하므로 lambda로
            val msg = handler.obtainMessage()
            val bundle = Bundle()
            val str = "hi my message"
            bundle.putString("str", str)
            msg.data = bundle
            handler.sendMessage(msg) //UI 변경할 내용을 handelr에게 보낸다.
        }

        val myThread = Thread(runnable)
        myThread.start()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
