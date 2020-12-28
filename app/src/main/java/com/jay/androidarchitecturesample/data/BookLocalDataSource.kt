package com.jay.androidarchitecturesample.data

import com.jay.androidarchitecturesample.model.Book

interface BookLocalDataSource {

    fun insertBooks(books: List<Book>)

    fun getBooks() : List<Book>

}