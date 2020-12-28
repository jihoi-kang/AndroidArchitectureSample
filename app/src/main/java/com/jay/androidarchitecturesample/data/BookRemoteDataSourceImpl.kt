package com.jay.androidarchitecturesample.data

import com.jay.androidarchitecturesample.api.NaverBookApiService
import com.jay.androidarchitecturesample.model.Book

class BookRemoteDataSourceImpl(
    private val bookApiService: NaverBookApiService,
) : BookRemoteDataSource {

    override suspend fun getBooks(query: String): List<Book> =
        bookApiService.getBooks(query).items

}