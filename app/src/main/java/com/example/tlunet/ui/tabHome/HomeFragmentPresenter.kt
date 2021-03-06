package com.example.tlunet.ui.tabHome

import android.util.Log
import com.example.tlunet.extensions.*
import com.example.tlunet.model.categories.Categories
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.utils.Preferences
import com.mespitech.mvpbase.coremvp.BasePresenter

class HomeFragmentPresenter : BasePresenter<HomeFragmentContract.View>(),HomeFragmentContract.Presenter {

    val interactor = HomeFragmentInteractor()
    val  listCNTT = mutableListOf<Subjects>()
    val  listKT = mutableListOf<Subjects>()
    val  listKTCK = mutableListOf<Subjects>()
    override fun fetchCategories() {
        interactor.getCategories {status, list ->
            if(status == successStatus){
                mView?.fillCategories(list!!)
            }
            else {
                Log.e("error", status)
            }
        }
    }

    override fun fetchSubjects() {
        mView?.showLoading()
        interactor.getSubjects { status, list ->
            if(status == successStatus){
                if(list?.size!! >0){
                    list.forEach {
                        when(it.categoriesCode){
                            CNTT -> listCNTT.add(it)
                            KT -> listKT.add(it)
                            KTCK -> listKTCK.add(it)
                        }
                    }
                }
                mView?.fillSubjects(listCNTT,listKT,listKTCK)
            }
            else {
                Log.e("error", status)
            }
            mView?.dismissLoading()
        }
    }

    override fun fetchUserData() {
        val email = Preferences.getInstance().getEmail()
            mView?.showLoading()
            interactor.getUserData(email!!) { status, data ->
            if(status == successStatus){
                Preferences.getInstance().saveUserData(data!!)
            }
            else {
                Log.e("error", status)
            }
            mView?.dismissLoading()
        }
    }

}