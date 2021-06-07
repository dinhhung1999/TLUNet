package com.mespitech.mvpbase.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMoreRecyclerView : RecyclerView {

    private var isLoading = false
    private var canLoadMore = true

    private var listener : (() -> Unit)? = null

    constructor(context: Context) : super(context) {init()}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {init()}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!canLoadMore || isLoading) {
                    return
                }
                if (layoutManager is LinearLayoutManager && (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() + 1 == adapter?.itemCount) {
                    listener?.invoke()
                } else if (layoutManager is GridLayoutManager && (layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition() + 1 == adapter?.itemCount) {
                    listener?.invoke()
                }
            }
        })
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    fun setCanLoadMore(canLoadMore: Boolean) {
        this.canLoadMore = canLoadMore
    }

    fun setOnLoadMoreListener(listener : (() -> Unit)) {
        this.listener = listener
    }
}
