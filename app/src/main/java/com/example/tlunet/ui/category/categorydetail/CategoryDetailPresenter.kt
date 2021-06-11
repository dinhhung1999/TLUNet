package com.example.tlunet.ui.category.categorydetail

import android.content.Intent
import android.util.Log
import com.example.tlunet.extensions.categoriesCode
import com.example.tlunet.extensions.successStatus
import com.mespitech.mvpbase.coremvp.BasePresenter

class CategoryDetailPresenter : BasePresenter<CategoryDetailActivityContract.View>(), CategoryDetailActivityContract.CategoryDetailPresenter {

    val interactor = CategoryDetailInteractor()

    override fun fetchSubjects(intent: Intent) {
        mView?.showLoading()
        val categoryCode = intent.getStringExtra(categoriesCode)
        interactor.getSubjects(categoryCode!!) {status, list ->
            mView?.dismissLoading()

            if(status == successStatus) {
                mView?.fillSubject(list!!)
            }
            else {
                Log.e("error", status)
            }
        }
    }
}