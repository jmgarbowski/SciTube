package com.norbsoft.testapp.core

interface PresenterLifecycle<V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}