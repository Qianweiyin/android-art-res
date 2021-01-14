package com.qwy.chapter_02.binderpool

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.IBinder.DeathRecipient
import android.os.RemoteException
import android.util.Log
import com.IBinderPool
import java.util.concurrent.CountDownLatch

class BinderPool private constructor(context: Context) {
    private var mContext: Context? = null
    private var mBinderPool: IBinderPool? = null
    private var mConnectBinderPoolCountDownLatch: CountDownLatch? = null

    @Synchronized
    private fun connectBinderPoolService() {
        mConnectBinderPoolCountDownLatch = CountDownLatch(1)
        val service = Intent(mContext, BinderPoolService::class.java)
        mContext?.bindService(
            service, mBinderPoolConnection,
            Context.BIND_AUTO_CREATE
        )
        try {
            mConnectBinderPoolCountDownLatch!!.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * query binder by binderCode from binder pool
     *
     * @param binderCode
     * the unique token of binder
     * @return binder who's token is binderCode<br></br>
     * return null when not found or BinderPoolService died.
     */
    fun queryBinder(binderCode: Int): IBinder? {
        var binder: IBinder? = null
        try {
            if (mBinderPool != null) {
                binder = mBinderPool!!.queryBinder(binderCode)
            }
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        return binder
    }

    private val mBinderPoolConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            // ignored.
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mBinderPool = IBinderPool.Stub.asInterface(service)
            try {
                mBinderPool?.asBinder()?.linkToDeath(mBinderPoolDeathRecipient, 0)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            mConnectBinderPoolCountDownLatch!!.countDown()
        }
    }
    private val mBinderPoolDeathRecipient: DeathRecipient = object : DeathRecipient {
        override fun binderDied() {
            Log.w(TAG, "binder died.")
            mBinderPool!!.asBinder().unlinkToDeath(this, 0)
            mBinderPool = null
            connectBinderPoolService()
        }
    }

    class BinderPoolImpl : IBinderPool.Stub() {
        @Throws(RemoteException::class)
        override fun queryBinder(binderCode: Int): IBinder {
            var binder: IBinder? = null
            when (binderCode) {
                BINDER_SECURITY_CENTER -> {
                    binder = SecurityCenterImpl()
                }
                BINDER_COMPUTE -> {
                    binder = ComputeImpl()
                }
                else -> {
                }
            }
            return binder!!
        }
    }

    companion object {
        private const val TAG = "BinderPool"
        const val BINDER_NONE = -1
        const val BINDER_COMPUTE = 0
        const val BINDER_SECURITY_CENTER = 1

        //        @Volatile
//        private var sInstance: BinderPool? = null
        private var sInstance: BinderPool? = null

        fun getInstance(context: Context): BinderPool {
            if (sInstance == null) {
                synchronized(BinderPool::class.java) {
                    if (sInstance == null) {
                        sInstance = BinderPool(context)
                    }
                }
            }
            return sInstance!!
        }
    }

    init {
        mContext = context.applicationContext
        connectBinderPoolService()
    }
}