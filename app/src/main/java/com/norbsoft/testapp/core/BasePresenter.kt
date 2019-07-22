package com.norbsoft.testapp.core

abstract class BasePresenter<V : BaseView> {

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}