package com.journaler

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.journaler.service.MainService


class Journaler: Application() {

    companion object {
        val tag = "JOURNALER"
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        Log.v(tag, "[ ON CREATE ]")
        startService()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
        /* If we get low on memory we will stop the service if running.
        Since the service is started as a STICKY, if we explicitly kill our application, the service will restart*/
        stopService()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.d(tag, "[ ON TRIM MEMORY ]: $level")
    }

    private fun startService() {
        val serviceIntent = Intent(this, MainService::class.java)
        startService(serviceIntent)
    }

    private fun stopService() {
        val serviceIntent = Intent(this, MainService::class.java)
        stopService(serviceIntent)
    }
}