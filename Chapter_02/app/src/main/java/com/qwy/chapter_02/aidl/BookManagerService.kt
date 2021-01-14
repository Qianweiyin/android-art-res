package com.qwy.chapter_02.aidl

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.qwy.chapter_02.IBookManager
import com.qwy.chapter_02.IOnNewBookArrivedListener
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean


/**
 * AIDL大致流程：
 * 首先创建一个Service和一个AIDL接口，接着创建一个类继承自AIDL接口中的
 * Stub类并实现Stub中的抽象方法，在Service的onBind方法中返回这个类的对象，
 * 然后客户端就可以绑定服务端Service，建立连接后就可以访问远程服务端的方法了。
 */


class BookManagerService : Service() {

    companion object {
        private const val TAG = "QwyAidl"
        private val mIsServiceDestroyed: AtomicBoolean = AtomicBoolean(false)
//        private val mIsServiceDestroyed = AtomicBoolean(false)

        //Remove explicit type arguments
        private val mBookList: CopyOnWriteArrayList<Book> = CopyOnWriteArrayList()

//        private val mListenerList = CopyOnWriteArrayList<IOnNewBookArrivedListener>()

        private val remoteCallbackList = RemoteCallbackList<IOnNewBookArrivedListener>()

    }


    private val mBinder: Binder = object : IBookManager.Stub() {
        override fun registerListener(listener: IOnNewBookArrivedListener?) {
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener)
//            } else {
//                Log.e(TAG, "already exists.")
//            }
//            Log.e(TAG, "registerListener,size: ${mListenerL   ist.size}")


            remoteCallbackList.register(listener);

        }

        override fun unregisterListener(listener: IOnNewBookArrivedListener?) {
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener)
//                Log.e(TAG, "unregister listener succeed.")
//            } else {
//                Log.e(TAG, "not found,can not unregister.")
//            }
//            Log.e(TAG, "unregisterListener,current size:" + mListenerList.size)


            val success: Boolean = remoteCallbackList.unregister(listener)
            if (success) {
                Log.d(TAG, "unregister success.")
            } else {
                Log.d(TAG, "not found, can not unregister.")
            }
            Log.d(TAG, "unregisterListener, current size:${remoteCallbackList.beginBroadcast()}")
            remoteCallbackList.unregister(listener)


        }

        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun getBookList(): MutableList<Book> {
            SystemClock.sleep(5000)
            return mBookList
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }


    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book(1, "Android"))
        mBookList.add(Book(2, "iOS"))

        Thread(ServiceWorker()).start()

    }


    override fun onDestroy() {
        mIsServiceDestroyed.set(true);
        super.onDestroy()
    }


    inner class ServiceWorker : Runnable {
        override fun run() {
            // do background processing here.....
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                val bookId: Int = mBookList.size + 1
                val newBook = Book(bookId, "new book# $bookId")
                try {
                    onNewBookArrived(newBook)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Throws(RemoteException::class)
    fun onNewBookArrived(book: Book) {
//        mBookList.add(book)
//        Log.d(TAG, "onNewBookArrived,notify listeners: ${mListenerList.size}")
//        for (i in 0 until mListenerList.size) {
////                val listener: IOnNewBookArrivedListener = mListenerList[i]
//            val listener = mListenerList[i]
//            Log.d(TAG, "onNewBookArrived,notify listener:$listener")
//            listener.onNewBookArrived(book)
//        }


        mBookList.add(book)
        val n: Int = remoteCallbackList.beginBroadcast()
        for (i in 0 until n) {
            val l: IOnNewBookArrivedListener = remoteCallbackList.getBroadcastItem(i)
            l.onNewBookArrived(book)
        }
        remoteCallbackList.finishBroadcast()
    }

}