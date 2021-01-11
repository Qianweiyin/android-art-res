// IOnNewBookArrivedListener.aidl
package com.qwy.chapter_02;

// Declare any non-default types here with import statements
import com.qwy.chapter_02.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}