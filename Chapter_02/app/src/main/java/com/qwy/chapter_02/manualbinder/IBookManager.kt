package com.qwy.chapter_02.manualbinder

import android.os.IBinder
import android.os.IInterface
import android.os.RemoteException
import com.qwy.chapter_02.aidl.Book

/**
 *
 * 手动写AIDL文件
 *
 * 声明一个AIDL性质的接口，只需要继承IInterface接口即可，IInterface接口中只有一个asBinder方法。
 */
interface IBookManager : IInterface {

    companion object {
        const val DESCRIPTOR = " com.qwy.chapter_02.manualbinder.IBookManager"
        const val TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0
        const val TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1
    }


    @Throws(RemoteException::class)
    fun getBookList(): List<Book?>?

    @Throws(RemoteException::class)
    fun addBook(book: Book?)


}