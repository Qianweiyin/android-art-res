package com.qwy.chapter_02.aidl

import android.os.Parcel
import android.os.Parcelable

open class Book() : Parcelable {
    //        var bookId: Int? = null
    var bookId: Int = 0 //存在堆里还是栈里
    var bookName: String? = null

    constructor(parcel: Parcel) : this() {
        bookId = parcel.readInt()
        bookName = parcel.readString()
    }

//    init {
//        bookId = parcel.readInt()
//        bookName = parcel.readString()
//    }

//    constructor(parcel: Parcel) : this() {
//    }

    constructor(bookId: Int, bookName: String?) : this() {
        this.bookId = bookId
        this.bookName = bookName
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookId)
        parcel.writeString(bookName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }


    override fun toString(): String {
        return String.format("[bookId:%s, bookName:%s]", bookId, bookName)
    }
}