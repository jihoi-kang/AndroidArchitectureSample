package com.jay.androidarchitecturesample.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NaverBookApiService {

    @GET("/v1/search/book")
    suspend fun getBooks(
        @Query("query") query: String
    ): NaverBookResponse

}