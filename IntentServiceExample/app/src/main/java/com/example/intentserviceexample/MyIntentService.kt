package com.example.intentserviceexample

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService: IntentService("MyIntentService") {
    override fun onHandleIntent(p0: Intent?) {
        Log.d("log", "log!!")
    }
}