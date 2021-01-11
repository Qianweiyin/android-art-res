// IBookManager.aidl
package com.qwy.chapter_02;

//import com.qwy.chapter_02.aidl.Book; //Failed to resolve 'Book'
import com.qwy.chapter_02.aidl.Book;
//IBookManager should be declared in a file called
interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}