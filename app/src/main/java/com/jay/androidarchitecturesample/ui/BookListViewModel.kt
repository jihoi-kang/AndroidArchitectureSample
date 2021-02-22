package com.jay.androidarchitecturesample.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleCoroutineScope
import com.jay.androidarchitecturesample.data.BookRepository
import com.jay.androidarchitecturesample.model.Book
import kotlinx.coroutines.launch

class BookListViewModel(
    private val lifecycleCoroutineScope: LifecycleCoroutineScope,
    private val bookRepository: BookRepository,
) {

    val bookItems = ObservableField<List<Book>>(emptyList())
    val loading = ObservableField(false)

    val hideKeyboardEvent = ObservableField<Unit>()

    fun getCachedBooks() {
        val books = bookRepository.getLocalBooks()
        bookItems.set(books)
    }

    fun getBooks(query: String) {
        if (query.isEmpty()) return

        lifecycleCoroutineScope.launch {
            hideKeyboardEvent.notifyChange()
            loading.set(true)
            val books = bookRepository.getBooks(query)
            loading.set(false)
            bookItems.set(books)
        }
    }

}