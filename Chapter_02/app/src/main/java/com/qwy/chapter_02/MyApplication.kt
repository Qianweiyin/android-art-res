package com.qwy.chapter_02

import android.app.Application
import android.os.Process
import android.util.Log

class MyApplication : Application() {


    companion object {
        private const val TAG = "MyApplication"
    }

    override fun onCreate() {
        super.onCreate()

        val processName = getProcessName(applicationContext, Process.myPid())

        Log.e(TAG, "application start , process name:  $processName")


    }


}