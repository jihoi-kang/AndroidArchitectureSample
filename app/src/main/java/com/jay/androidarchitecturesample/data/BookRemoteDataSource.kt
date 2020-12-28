package com.jay.androidarchitecturesample.data

import com.jay.androidarchitecturesample.model.Book

interface BookRemoteDataSource {

    suspend fun getBooks(query: String): List<Book>

}