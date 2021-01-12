package com.qwy.chapter_02.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_02.IBookManager
import com.qwy.chapter_02.IOnNewBookArrivedListener
import com.qwy.chapter_02.R
import kotlin.concurrent.thread


class BookManagerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "QwyAidl"
        private const val MESSAGE_NEW_BOOK_ARRIVED = 1

    }


    private var mRemoteBookManager: IBookManager? = null


    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_NEW_BOOK_ARRIVED -> {
                    Log.d(TAG, "receive new book :" + msg.obj)
                }
                else -> super.handleMessage(msg)
            }
        }
    }


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mRemoteBookManager = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val bookManager = IBookManager.Stub.asInterface(service)
            mRemoteBookManager = bookManager


            val list = bookManager.bookList
            Log.e(TAG, "query book list,list type:${list.javaClass.canonicalName}")
            Log.e(TAG, "query book list:${list.toString()}")

            val newBook = Book(3, "Android开发艺术探索")
            bookManager.addBook(newBook)
            Log.e(TAG, "add book: $newBook")
            val newList = bookManager.bookList
            Log.e(TAG, "query book newList : ${newList.toString()}")

            bookManager.registerListener(mOnNewBookArrivedListener);

        }

    }


    private val mOnNewBookArrivedListener: IOnNewBookArrivedListener =
        object : IOnNewBookArrivedListener.Stub() {
            @Throws(RemoteException::class)
            override fun onNewBookArrived(newBook: Book) {
                mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_manager)
        val intent = Intent(this, BookManagerService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        if (mRemoteBookManager != null
            && mRemoteBookManager!!.asBinder().isBinderAlive
        ) {
            Log.e(TAG, "unregister listener:$mOnNewBookArrivedListener")
            mRemoteBookManager!!.unregisterListener(mOnNewBookArrivedListener)
        }
        unbindService(serviceConnection)
        super.onDestroy()
    }


    fun onButton1Click(view: View?) {
        Toast.makeText(this, "click button1", Toast.LENGTH_SHORT).show();
//        Thread(Runnable {
//        }).start()

        thread {
            mRemoteBookManager?.bookList
        }
    }
}