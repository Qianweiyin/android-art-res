package com.qwy.chapter_02.manualbinder

import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.os.RemoteException
import com.qwy.chapter_02.aidl.Book
import com.qwy.chapter_02.aidl.Book.CREATOR.createFromParcel

class BookManagerImpl : Binder(), IBookManager {
    @Throws(RemoteException::class)
    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        when (code) {
            IBinder.INTERFACE_TRANSACTION -> {
                reply!!.writeString(IBookManager.DESCRIPTOR)
                return true
            }
            IBookManager.TRANSACTION_getBookList -> {
                data.enforceInterface(IBookManager.DESCRIPTOR)
                val result =
                    getBookList()
                reply!!.writeNoException()
                reply.writeTypedList(result)
                return true
            }
            IBookManager.TRANSACTION_addBook -> {
                data.enforceInterface(IBookManager.DESCRIPTOR)
                val arg0: Book?
                arg0 = if (0 != data.readInt()) {
                    createFromParcel(data)
                } else {
                    null
                }
                addBook(arg0)
                reply!!.writeNoException()
                return true
            }
        }
        return super.onTransact(code, data, reply, flags)
    }

    @Throws(RemoteException::class)
    override fun getBookList(): List<Book?>? {
        // TODO 待实现
        return null
    }

    @Throws(RemoteException::class)
    override fun addBook(book: Book?) {
        // TODO 待实现
    }

    override fun asBinder(): IBinder {
        return this
    }

    override fun getInterfaceDescriptor(): String? {
        return IBookManager.DESCRIPTOR
    }

    private class Proxy internal constructor(private val mRemote: IBinder) : IBookManager {
        override fun asBinder(): IBinder {
            return mRemote
        }

        val interfaceDescriptor: String get() = IBookManager.DESCRIPTOR

        @Throws(RemoteException::class)
        override fun getBookList(): List<Book?>? {
            val data = Parcel.obtain()
            val reply = Parcel.obtain()
            val result: List<Book?>?
            result = try {
                data.writeInterfaceToken(IBookManager.DESCRIPTOR)
                mRemote.transact(
                    IBookManager.TRANSACTION_getBookList,
                    data,
                    reply,
                    0
                )
                reply.readException()
                reply.createTypedArrayList(Book)
            } finally {
                reply.recycle()
                data.recycle()
            }
            return result
        }

        @Throws(RemoteException::class)
        override fun addBook(book: Book?) {
            val data = Parcel.obtain()
            val reply = Parcel.obtain()
            try {
                data.writeInterfaceToken(IBookManager.DESCRIPTOR)
                if (book != null) {
                    data.writeInt(1)
                    book.writeToParcel(data, 0)
                } else {
                    data.writeInt(0)
                }
                mRemote.transact(
                    IBookManager.TRANSACTION_addBook,
                    data,
                    reply,
                    0
                )
                reply.readException()
            } finally {
                reply.recycle()
                data.recycle()
            }
        }

    }

    companion object {
        /**
         * Cast an IBinder object into an IBookManager interface, generating a proxy
         * if needed.
         */
        fun asInterface(obj: IBinder?): IBookManager? {
            if (obj == null) {
                return null
            }
            val iin = obj.queryLocalInterface(IBookManager.DESCRIPTOR)
            return if (iin != null && iin is IBookManager) {
                iin
            } else Proxy(obj)
        }
    }

    /**
     * Construct the stub at attach it to the interface.
     */
    init {
        attachInterface(
            this,
            IBookManager.DESCRIPTOR
        )
    }
}