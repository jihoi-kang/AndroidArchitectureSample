package com.jay.androidarchitecturesample.model

import androidx.room.Entity

@Entity(primaryKeys = [("title")])
data class Book(
    val author: String,
    val description: String,
    val discount: String,
    val image: String,
    val isbn: String,
    val link: String,
    val price: String,
    val pubdate: String,
    val publisher: String,
    val title: String
)