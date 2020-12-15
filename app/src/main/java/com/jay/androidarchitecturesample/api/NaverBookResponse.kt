package com.jay.androidarchitecturesample.api

import com.jay.androidarchitecturesample.model.Book

data class NaverBookResponse(
    val display: Int,
    val items: List<Book>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)