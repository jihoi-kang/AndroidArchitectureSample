package com.jay.androidarchitecturesample.data

import com.jay.androidarchitecturesample.model.Book
import com.jay.androidarchitecturesample.room.BookDao

class BookLocalDataSourceImpl(
    private val bookDao: BookDao
) : BookLocalDataSource {

    override fun insertBooks(books: List<Book>) {
        bookDao.insertBookList(books)
    }

    override fun getBooks(): List<Book> =
        bookDao.getBooks()

}