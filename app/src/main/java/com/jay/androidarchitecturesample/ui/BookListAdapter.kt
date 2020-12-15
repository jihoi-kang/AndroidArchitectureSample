package com.jay.androidarchitecturesample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jay.androidarchitecturesample.R
import com.jay.androidarchitecturesample.model.Book


class BookListAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<BookViewHolder>() {

    private val books: MutableList<Book> = mutableListOf()

    fun setBooks(books: List<Book>) {
        this.books.clear()
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

}