package com.jay.androidarchitecturesample.ui

import com.jay.androidarchitecturesample.base.BaseContract
import com.jay.androidarchitecturesample.model.Book

interface BookListContract {

    interface View : BaseContract.View {

        fun hideKeyboard()
        fun showBookItems()
        fun showNoResult()
        fun setBookItems(books: List<Book>)

    }

    interface Presenter : BaseContract.Presenter {

        fun getCachedBooks()
        suspend fun getBooks(query: String)

    }

}