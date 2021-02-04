package com.jay.androidarchitecturesample.ui

import com.jay.androidarchitecturesample.model.Book

interface BookListContract {

    interface View {

        fun hideKeyboard()
        fun showLoading()
        fun hideLoading()
        fun showBookItems()
        fun showNoResult()
        fun setBookItems(books: List<Book>)

    }

    interface Presenter {

        fun getCachedBooks()
        suspend fun getBooks(query: String)

    }

}