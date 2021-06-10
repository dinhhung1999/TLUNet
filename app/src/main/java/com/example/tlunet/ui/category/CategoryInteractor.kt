package com.example.tlunet.ui.category

import android.annotation.SuppressLint
import android.util.Log
import com.example.tlunet.extensions.categories
import com.example.tlunet.extensions.errStatus
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.http.FireStoreService
import com.example.tlunet.model.categories.Categories
import com.example.tlunet.utils.getObservable

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryInteractor : CategoryActivityContract.Interactor {
    @SuppressLint("CheckResult")
    override fun getCategories(callback: (status: String, List<Categories>?) -> Unit) {
            val categoryDB = FireStoreService.db.collection(categories)
            categoryDB.getObservable<Categories>()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ documents ->
                        if (documents != null) {
                            callback(successStatus, documents)
                        } else {
                            callback(errStatus, null)
                        }
                    }, { exception ->
                        Log.e("firebase", "Error getting data", exception)
                        callback(errStatus, null)
                    })
    }
}