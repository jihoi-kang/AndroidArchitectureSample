package com.jay.androidarchitecturesample.ui

import androidx.recyclerview.widget.RecyclerView
import com.jay.androidarchitecturesample.BR
import com.jay.androidarchitecturesample.databinding.ItemBookBinding
import com.jay.androidarchitecturesample.model.Book

class BookViewHolder(
    private val binding: ItemBookBinding,
    private val vm: BookListViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        with(binding) {
            setVariable(BR.item, book)
            setVariable(BR.viewModel, vm)
            executePendingBindings()
        }
    }

}