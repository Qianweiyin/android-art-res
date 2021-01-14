package com.qwy.chapter_02.binderpool

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.qwy.chapter_02.binderpool.BinderPool.BinderPoolImpl

class BinderPoolService : Service() {


    private val TAG = "BinderPoolService"

    private val mBinderPool: Binder = BinderPoolImpl()

    override fun onBind(intent: Intent?): IBinder? {
        return mBinderPool
    }

}