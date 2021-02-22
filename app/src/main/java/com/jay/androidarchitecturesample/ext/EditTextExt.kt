package com.jay.androidarchitecturesample.ext

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("onEditorListener")
fun EditText.bindOnEditorListener(action: (() -> Unit)?) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            action?.invoke()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}