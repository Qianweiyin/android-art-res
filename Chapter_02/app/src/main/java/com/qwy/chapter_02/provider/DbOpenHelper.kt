package com.qwy.chapter_02.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//None of the following functions can be called with the arguments supplied.
class DbOpenHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "book_provider.db"
        private const val BOOK_TABLE_NAME = "book"
        private const val USER_TABLE_NAME = "user"

        /// 图书和用户信息表
        private const val CREATE_BOOK_TABLE = ("CREATE TABLE IF NOT EXISTS "
                + BOOK_TABLE_NAME) + "(_id INTEGER PRIMARY KEY,name TEXT)"

        //Private property name'CREATE_USER_TABLE' should not contain underscores in the middle or the end
        private const val CREATE_USER_TABLE = ("CREATE TABLE IF NOT EXISTS "
                + USER_TABLE_NAME) + "(_id INTEGER PRIMARY KEY," + "name TEXT," + "sex INT)"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOK_TABLE)
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // TODO ignored
    }
}