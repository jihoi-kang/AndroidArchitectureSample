package com.jay.androidarchitecturesample.ui

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.androidarchitecturesample.R
import com.jay.androidarchitecturesample.model.Book
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class BookViewHolder(
    view: View,
    private val onItemClick: (String) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val ivCover: ImageView = view.findViewById(R.id.iv_cover)
    private val tvPubDate: TextView = view.findViewById(R.id.tv_pub_date)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvDetailInfo: TextView = view.findViewById(R.id.tv_detail_info)
    private val tvPrice: TextView = view.findViewById(R.id.tv_price)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)

    fun bind(book: Book) {
        Glide.with(ivCover.context)
            .load(book.image)
            .into(ivCover)

        val date = SimpleDateFormat("yyyyMMdd").parse(book.pubdate)
        tvPubDate.text = SimpleDateFormat("yyyy-MM-dd").format(date)
        tvTitle.text = getHtmlText(book.title)
        tvDetailInfo.text = "${getHtmlText(book.author)} | ${book.publisher}"

        val price = if (book.price.contains(".")) {
            book.price.substring(0, book.price.indexOf(".")).toLong()
        } else {
            book.price.toLong()
        }
        val commaFormat = DecimalFormat("###,###") //콤마
        tvPrice.text =
            String.format(itemView.context.getString(R.string.price_fmt), commaFormat.format(price))
        tvDescription.text = getHtmlText(book.description)

        itemView.setOnClickListener {
            onItemClick(book.link)
        }
    }

    @Suppress("DEPRECATION")
    private fun getHtmlText(text: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(text)
        }

}