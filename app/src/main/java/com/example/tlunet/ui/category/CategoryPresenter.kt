package com.example.tlunet.ui.category

import android.util.Log
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.ui.tabHome.HomeFragmentInteractor
import com.mespitech.mvpbase.coremvp.BasePresenter

class CategoryPresenter : BasePresenter<CategoryActivityContract.View>(), CategoryActivityContract.Presenter {
    val interactor = HomeFragmentInteractor()
    override fun fetchCategories() {
        mView?.showLoading()
        interactor.getCategories {status, list ->
            mView?.dismissLoading()
            if(status == successStatus){
                mView?.fillCategories(list!!)
            }
            else {
                Log.e("error", status)
            }
        }
    }
}