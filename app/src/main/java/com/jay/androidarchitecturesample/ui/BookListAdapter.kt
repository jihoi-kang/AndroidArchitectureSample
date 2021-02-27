package com.jay.androidarchitecturesample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.androidarchitecturesample.R
import com.jay.androidarchitecturesample.databinding.ItemBookBinding
import com.jay.androidarchitecturesample.model.Book

class BookListAdapter(
    private val viewModel: BookListViewModel
) : RecyclerView.Adapter<BookViewHolder>() {

    private val books: MutableList<Book> = mutableListOf()

    fun setBooks(books: List<Book>) {
        this.books.clear()
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = DataBindingUtil.inflate<ItemBookBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_book,
            parent,
            false
        )

        return BookViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

}