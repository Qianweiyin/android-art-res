package com.qwy.chapter_02.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log


/**
 * This type has a constructor, and thus must be initialized here
 *
 * Change to constructor invocation
 */
class BookProvider : ContentProvider() {


    private var mDb: SQLiteDatabase? = null
    private var mContext: Context? = null

    companion object {
        private const val TAG = "MyApplication"

        const val AUTHORITY = "com.qwy.chapter_02.provider"


        private const val BOOK_TABLE_NAME = "book"
        private const val USER_TABLE_NAME = "user"

        private const val DB_VERSION = 1

        private const val CREATE_BOOK_TABLE = ("CREATE TABLE IF NOT EXISTS "
                + BOOK_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT)")

        private const val CREATE_USER_TABLE = ("CREATE TABLE IF NOT EXISTS "
                + USER_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT,"
                + "sex INT)")

        const val BOOK_URI_CODE = 0
        const val USER_URI_CODE = 1

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    /**
     * 为book表和user表分别指定URI,
     * 分别为"content://com.qwy.chapter_02.provider/book"
     *    和"content://com.qwy.chapter_02.provider/user"
     * 这两个URI所关联的Uri_Code分别为0和1
     */
    init {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE)
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.e(TAG, "insert")
        val table = getTableName(uri) ?: throw  IllegalArgumentException("Unsupported URI:$uri")
        mDb?.insert(table, null, values)

        mContext?.contentResolver?.notifyChange(uri, null)

        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.e(TAG, "query,current thread:${Thread.currentThread().name}")

        val table: String? =
            getTableName(uri) ?: throw  IllegalArgumentException("Unsupported URI: $uri")

        return mDb?.query(
            table,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder,
            null
        )
    }

    override fun onCreate(): Boolean {
        Log.e(TAG, "onCreate,current thread: ${Thread.currentThread().name}")

        mContext = context

        //ContentProvider创建时，初始化数据库。
        // 注意：这里仅仅是为了演示，实际使用中不推荐在主线程中进行耗时的数据库操作
        initProviderData()

        return false
    }

    private fun initProviderData() {

        //Smart cast to 'SQLiteDatabase!' is impossible, because 'mDb' is a mutable property that could have been changed by this time

        //let run apply with also 作用域函数

        //mDb = DbOpenHelper(mContext!!).writableDatabase
        mDb = mContext?.let { DbOpenHelper(it).writableDatabase }

        mDb?.run {
            execSQL("delete from $BOOK_TABLE_NAME")
            execSQL("delete from $USER_TABLE_NAME")
            execSQL("insert into book values(3,'Android');")
            execSQL("insert into book values(4,'Ios');")
            execSQL("insert into book values(5,'Html5');")
            execSQL("insert into user values(1,'jake',1);")
            execSQL("insert into user values(2,'jasmine',0);")
        }

    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.e(TAG, "update")
        val table =
            getTableName(uri) ?: throw java.lang.IllegalArgumentException("Unsupported URI: $uri")
        val row = mDb!!.update(table, values, selection, selectionArgs)
        if (row > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return row
    }

    //  PackageManager.DexOptimizer: Well this is awkward; package com.qwy.chapter_02.MyApplication had UID -1 java.lang.Throwable
    //(权限问题)
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.e(TAG, "delete")
        val table =
            getTableName(uri) ?: throw java.lang.IllegalArgumentException("Unsupported URI: $uri")
        val count = mDb!!.delete(table, selection, selectionArgs)
        if (count > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return count
    }

    override fun getType(uri: Uri): String? {
        Log.e(TAG, "getType")
        return null
    }


    private fun getTableName(uri: Uri): String? {
        var tableName: String? = null
        when (sUriMatcher.match(uri)) {
            BOOK_URI_CODE -> tableName = BOOK_TABLE_NAME
            USER_URI_CODE -> tableName = USER_TABLE_NAME
            else -> {
            }
        }
        return tableName
    }
}