package com.jay.androidarchitecturesample.ext

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jay.androidarchitecturesample.R
import java.text.DecimalFormat

@Suppress("DEPRECATION")
@BindingAdapter("htmlText")
fun TextView.bindHtmlText(text: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(text)
    }
}

@BindingAdapter("priceText")
fun TextView.bindPriceText(price: String) {
    val price = if (price.contains(".")) {
        price.substring(0, price.indexOf(".")).toLong()
    } else {
        price.toLong()
    }
    val commaFormat = DecimalFormat("###,###")
    this.text =
        String.format(this.context.getString(R.string.price_fmt), commaFormat.format(price))
}