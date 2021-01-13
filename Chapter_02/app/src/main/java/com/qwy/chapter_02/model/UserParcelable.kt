package com.qwy.chapter_02.model

import android.os.Parcel
import android.os.Parcelable
import com.qwy.chapter_02.aidl.Book

class UserParcelable() : Parcelable {

    var userId = 0
    var userName: String? = null
    var isMale = false

    var book: Book? = null

    constructor(parcel: Parcel) : this() {
    }


    //序列化工作由writeToParcel完成,最终是通过Parcel中的一系列write方法完成的
    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(userId)
        parcel.writeString(userName)
        parcel.writeInt(if (isMale) 1 else 0)


    }

    override fun describeContents(): Int {
        return 0
    }

    // 反序列化
    companion object CREATOR : Parcelable.Creator<UserParcelable> {
        override fun createFromParcel(parcel: Parcel): UserParcelable {
            return UserParcelable(parcel)
        }

        override fun newArray(size: Int): Array<UserParcelable?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return String.format(
            "User:{userId:%s, userName:%s, isMale:%s}, with child:{%s}",
            userId,
            userName,
            isMale,
            book
        )
    }
}