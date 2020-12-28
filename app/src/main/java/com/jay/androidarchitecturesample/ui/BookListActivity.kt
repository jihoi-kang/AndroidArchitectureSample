package com.jay.androidarchitecturesample.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.jay.androidarchitecturesample.R
import com.jay.androidarchitecturesample.api.RetrofitHelper
import com.jay.androidarchitecturesample.data.*
import com.jay.androidarchitecturesample.room.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class BookListActivity : AppCompatActivity() {

    private val bookRepository: BookRepository by lazy {
        val bookRemoteDataSource: BookRemoteDataSource =
            BookRemoteDataSourceImpl(RetrofitHelper.naverBookApiService)
        val bookLocalDataSource: BookLocalDataSource =
            BookLocalDataSourceImpl(AppDatabase.getInstance(this).bookDao())
        BookRepositoryImpl(bookRemoteDataSource, bookLocalDataSource)
    }

    private val bookListAdapter: BookListAdapter by lazy {
        BookListAdapter { link ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
    }

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()
    }

    private fun setupUi() {
        rv_book_list.adapter = bookListAdapter

        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getBooks(et_search.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        iv_search.setOnClickListener {
            getBooks(et_search.text.toString())
        }
    }

    private fun getBooks(query: String) {
        if (query.isEmpty()) return

        lifecycleScope.launch {
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            pb_loading.isVisible = true
            val books = bookRepository.getBooks(query)
            if (books.isEmpty()) {
                tv_no_result.isVisible = true
                rv_book_list.isGone = true
            } else {
                tv_no_result.isGone = true
                rv_book_list.isVisible = true

                bookListAdapter.setBooks(books)
            }
            pb_loading.isGone = true
        }
    }
}