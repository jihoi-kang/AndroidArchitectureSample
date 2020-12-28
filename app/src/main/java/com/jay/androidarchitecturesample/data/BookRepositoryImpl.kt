package com.jay.androidarchitecturesample.data

import com.jay.androidarchitecturesample.model.Book

class BookRepositoryImpl(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource,
) : BookRepository {

    override suspend fun getBooks(query: String): List<Book> {
        val books = bookRemoteDataSource.getBooks(query)
        bookLocalDataSource.insertBooks(books)

        return books
    }

    override fun getLocalBooks(): List<Book> =
        bookLocalDataSource.getBooks()

}