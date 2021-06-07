package com.mespitech.mvpbase.coremvp

open class BasePresenter<V : BaseView> {

    protected var mView: V? = null

    fun attachView(view: V) {
        this.mView = view
    }

    fun detachView() {
        this.mView = null
    }
}
