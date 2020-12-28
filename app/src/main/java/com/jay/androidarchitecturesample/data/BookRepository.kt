package com.jay.androidarchitecturesample.data

import com.jay.androidarchitecturesample.model.Book

interface BookRepository {

    suspend fun getBooks(query: String): List<Book>

    fun getLocalBooks(): List<Book>

}