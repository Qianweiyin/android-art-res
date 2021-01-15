package com.qwy.chapter_02.binderpool

import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ICompute
import com.ISecurityCenter
import com.qwy.chapter_02.R
import kotlin.concurrent.thread


class BinderPoolActivity : AppCompatActivity() {
    companion object {
        private val TAG = "QwyBinderPool"
    }

    private var mSecurityCenter: ISecurityCenter? = null
    private var mCompute: ICompute? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_pool)

        thread {
            doWork()
        }
    }


    fun doWork() {

        val binderPool: BinderPool = BinderPool.getInstance(this@BinderPoolActivity)
        val securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER)


        mSecurityCenter = ISecurityCenter.Stub.asInterface(securityBinder)
        Log.e(TAG, "visit ISecurityCenter")
        val msg = "helloworld-安卓"
        println("content:$msg")
        try {
            val password = mSecurityCenter!!.encrypt(msg)
            println("encrypt:$password")
            println("decrypt:" + mSecurityCenter!!.decrypt(password))
        } catch (e: RemoteException) {
            Log.e(TAG, "RemoteException 1 : " + e.message)
            e.printStackTrace()
        }

        Log.e(TAG, "visit ICompute")
        val computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE)
        mCompute = ICompute.Stub.asInterface(computeBinder)
        try {
            println("3+5=" + mCompute!!.add(3, 5))
        } catch (e: RemoteException) {
            Log.e(TAG, "RemoteException 2 : " + e.message)
            e.printStackTrace()
        }
    }
}