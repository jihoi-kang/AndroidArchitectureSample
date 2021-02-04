package com.jay.androidarchitecturesample.ui

import com.jay.androidarchitecturesample.base.BasePresenter
import com.jay.androidarchitecturesample.data.BookRepository

class BookListPresenter(
    private val view: BookListContract.View,
    private val bookRepository: BookRepository,
) : BasePresenter(), BookListContract.Presenter {

    override fun getCachedBooks() {
        val books = bookRepository.getLocalBooks()
        if (books.isEmpty()) {
            view.showNoResult()
        } else {
            view.showBookItems()
            view.setBookItems(books)
        }
    }

    override suspend fun getBooks(query: String) {
        if (query.isEmpty()) return

        view.hideKeyboard()
        view.showLoading()
        val books = bookRepository.getBooks(query)
        view.hideLoading()
        if (books.isEmpty()) {
            view.showNoResult()
        } else {
            view.showBookItems()
            view.setBookItems(books)
        }
    }

}