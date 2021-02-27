package com.jay.androidarchitecturesample.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadUrl")
fun ImageView.bindLoadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}