package com.qwy.chapter_02.provider

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.qwy.chapter_02.R
import com.qwy.chapter_02.aidl.Book
import com.qwy.chapter_02.model.UserParcelable


class ProviderActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "ProviderActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)
        val uri: Uri = Uri.parse("content://com.qwy.chapter_02.provider")


//        This Cursor should be freed up after use with #close()
//        contentResolver.query(uri, null, null, null, null)
//        contentResolver.query(uri, null, null, null, null)
//        contentResolver.query(uri, null, null, null, null)


        val bookUri = Uri.parse("content://com.qwy.chapter_02.provider/book")
        val values = ContentValues()
        values.put("_id", 6)
        values.put("name", "程序设计的艺术")
        contentResolver.insert(bookUri, values)
        val bookCursor: Cursor? =
            contentResolver.query(bookUri, arrayOf("_id", "name"), null, null, null)

//        while (bookCursor!!.moveToNext()) {
//            val book = Book()
//            book.bookId = bookCursor.getInt(0)
//            book.bookName = bookCursor.getString(1)
//            Log.e(TAG, "query book: ${book.toString()}")
//        }
//        bookCursor.close()


        bookCursor?.use {
            while (it.moveToNext()) {
                val book = Book()
                book.bookId = it.getInt(0)
                book.bookName = it.getString(1)
                Log.e(TAG, "query book: ${book.toString()}")
            }
        }


//        bookCursor?.let {
//            while (it.moveToFirst()) {
//                val book = Book()
//                book.bookId = it.getInt(0)
//                book.bookName = it.getString(1)
//                Log.e(TAG, "query book: ${book.toString()}")
//            }
//            it.close()
//        }


        val userUri =
            Uri.parse("content://com.qwy.chapter_02.provider/user")
        val userCursor =
            contentResolver.query(userUri, arrayOf("_id", "name", "sex"), null, null, null)
//        while (userCursor!!.moveToNext()) {
//            val user = UserParcelable()
//            user.userId = userCursor.getInt(0)
//            user.userName = userCursor.getString(1)
//            user.isMale = userCursor.getInt(2) == 1
//            Log.e(TAG, "query user:${user.toString()}")
//        }
//        userCursor.close()


        userCursor?.use {
            while (it.moveToNext()) {
                val user = UserParcelable()
                user.userId = it.getInt(0)
                user.userName = it.getString(1)
                user.isMale = it.getInt(2) == 1
                Log.e(TAG, "query user:${user.toString()}")
            }
        }


        //Unnecessary non-null assertion (!!) on a non-null receiver of type Cursor
//        userCursor?.let {
//            while (it.moveToNext()) {
//                val user = UserParcelable()
//                user.userId = it.getInt(0)
//                user.userName = it.getString(1)
//                user.isMale = it.getInt(2) == 1
//                Log.e(TAG, "query user:${user.toString()}")
//            }
//            it.close()
//        }


    }


}