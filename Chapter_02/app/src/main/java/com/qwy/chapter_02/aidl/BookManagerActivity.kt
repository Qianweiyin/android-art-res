package com.qwy.chapter_02.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_02.IBookManager
import com.qwy.chapter_02.R


class BookManagerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "QwyAidl"
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val bookManager = IBookManager.Stub.asInterface(service)
            val list = bookManager.bookList
            Log.e(TAG, "query book list,list type:${list.javaClass.canonicalName}")
            Log.e(TAG, "query book list:${list.toString()}")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_manager)
        val intent = Intent(this, BookManagerService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        unbindService(serviceConnection)
        super.onDestroy()
    }

}