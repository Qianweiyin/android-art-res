package com.qwy.chapter_02.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log


/**
 * This type has a constructor, and thus must be initialized here
 *
 * Change to constructor invocation
 */
class BookProvider : ContentProvider() {

    companion object {
        private const val TAG = "MyApplication"
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.e(TAG, "insert")
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.e(TAG, "query,current thread:${Thread.currentThread().name}")
        return null
    }

    override fun onCreate(): Boolean {
        Log.e(TAG, "onCreate,current thread: ${Thread.currentThread().name}")
        return false
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.e(TAG, "update")
        return 0
    }
//  PackageManager.DexOptimizer:   Well this is awkward; package com.qwy.chapter_02.MyApplication had UID -1 java.lang.Throwable
    //(权限问题)
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.e(TAG, "delete")
        return 0
    }

    override fun getType(uri: Uri): String? {
        Log.e(TAG, "getType")
        return null
    }
}