package com.jay.androidarchitecturesample.base

import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible

abstract class BaseActivity<P : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View {

    protected var progressBar: ProgressBar? = null
    protected abstract val presenter: P

    override fun showLoading() {
        progressBar?.isVisible = true
    }

    override fun hideLoading() {
        progressBar?.isGone = true
    }

    override fun onDestroy() {
        progressBar = null
        super.onDestroy()
    }

}