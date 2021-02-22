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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.jay.androidarchitecturesample.R
import com.jay.androidarchitecturesample.api.RetrofitHelper
import com.jay.androidarchitecturesample.data.*
import com.jay.androidarchitecturesample.databinding.ActivityBookListBinding
import com.jay.androidarchitecturesample.model.Book
import com.jay.androidarchitecturesample.room.AppDatabase
import kotlinx.coroutines.launch

class BookListActivity : AppCompatActivity(), BookListContract.View {

    private lateinit var binding: ActivityBookListBinding

    private val bookRepository: BookRepository by lazy {
        val bookRemoteDataSource: BookRemoteDataSource =
            BookRemoteDataSourceImpl(RetrofitHelper.naverBookApiService)
        val bookLocalDataSource: BookLocalDataSource =
            BookLocalDataSourceImpl(AppDatabase.getInstance(this).bookDao())
        BookRepositoryImpl(bookRemoteDataSource, bookLocalDataSource)
    }

    private val presenter: BookListContract.Presenter by lazy {
        BookListPresenter(this, bookRepository)
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

        setupUi()
        presenter.getCachedBooks()
    }

    private fun setupUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list)

        binding.rvBookList.adapter = bookListAdapter
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                lifecycleScope.launch {
                    presenter.getBooks(binding.etSearch.text.toString())
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.ivSearch.setOnClickListener {
            lifecycleScope.launch {
                presenter.getBooks(binding.etSearch.text.toString())
            }
        }
    }

    override fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showLoading() {
        binding.pbLoading.isVisible = true
    }

    override fun hideLoading() {
        binding.pbLoading.isGone = true
    }

    override fun showBookItems() {
        binding.tvNoResult.isGone = true
        binding.rvBookList.isVisible = true
    }

    override fun showNoResult() {
        binding.tvNoResult.isVisible = true
        binding.rvBookList.isGone = true
    }

    override fun setBookItems(books: List<Book>) {
        bookListAdapter.setBooks(books)
    }

}