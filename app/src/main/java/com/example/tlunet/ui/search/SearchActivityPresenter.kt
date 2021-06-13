package com.example.tlunet.ui.search

import com.example.tlunet.extensions.successStatus
import com.mespitech.mvpbase.coremvp.BasePresenter

class SearchActivityPresenter : BasePresenter<SearchActivityContract.View>(), SearchActivityContract.Presenter {
    val interactor = SearchActivityInteractor()
    override fun searchSubject(subjectName: String) {
        mView?.showLoading()
        interactor.getSubject(subjectName) {status, list ->
            mView?.dismissLoading()
            if (status == successStatus) {
                mView?.fillSubjects(list!!)
            }
            else {
                mView?.onError("Tìm kiếm thất bại")
            }
        }
    }
}