package com.jay.androidarchitecturesample.base

abstract class BaseContract {

    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter

}