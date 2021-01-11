package com.qwy.chapter_02.aidl

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.qwy.chapter_02.IBookManager
import java.util.concurrent.CopyOnWriteArrayList

class BookManagerService : Service() {

    companion object {
        private const val TAG = "QwyAidl"
    }

    //Remove explicit type arguments
    private val mBookList: CopyOnWriteArrayList<Book> = CopyOnWriteArrayList()

    private val mBinder: Binder = object : IBookManager.Stub() {
        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun getBookList(): MutableList<Book> {
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
    }

}