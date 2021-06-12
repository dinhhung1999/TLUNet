package com.example.tlunet.ui.tabProfile

import com.example.tlunet.extensions.successStatus
import com.mespitech.mvpbase.coremvp.BasePresenter

class ProfileFragmentPresenter: BasePresenter<ProfileFragmentContract.View>(),ProfileFragmentContract.Presenter {
    val interactor = ProfileFragmentInteractor()
    override fun logout() {
        mView?.showLoading()
        interactor.logout { status ->
            if(status == successStatus){
                mView?.onSuccess()
            }
        }
    }
}