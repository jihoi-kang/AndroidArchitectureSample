package com.jay.androidarchitecturesample.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.lifecycleScope
import com.jay.androidarchitecturesample.R
import com.jay.androidarchitecturesample.api.RetrofitHelper
import com.jay.androidarchitecturesample.data.*
import com.jay.androidarchitecturesample.databinding.ActivityBookListBinding
import com.jay.androidarchitecturesample.room.AppDatabase

class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding

    private val bookRepository: BookRepository by lazy {
        val bookRemoteDataSource: BookRemoteDataSource =
            BookRemoteDataSourceImpl(RetrofitHelper.naverBookApiService)
        val bookLocalDataSource: BookLocalDataSource =
            BookLocalDataSourceImpl(AppDatabase.getInstance(this).bookDao())
        BookRepositoryImpl(bookRemoteDataSource, bookLocalDataSource)
    }

    private val viewModel: BookListViewModel by lazy {
        BookListViewModel(lifecycleScope, bookRepository)
    }

    private val bookListAdapter: BookListAdapter by lazy {
        BookListAdapter(viewModel)
    }

    private val inputMethodManager: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUi()
        setupObserve()
        viewModel.getCachedBooks()
    }

    private fun setupUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list)
        binding.vm = viewModel
        binding.rvBookList.adapter = bookListAdapter
    }

    private fun setupObserve() {
        viewModel.hideKeyboardEvent
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                }
            })
        viewModel.openBookDetailEvent
            .addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.openBookDetailEvent.get()))
                    )
                }
            })
        viewModel.bookItems.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                bookListAdapter.setBooks(viewModel.bookItems.get() ?: emptyList())
            }
        })
    }

}