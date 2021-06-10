package com.example.tlunet.ui.category.categorydetail

import android.annotation.SuppressLint
import android.util.Log
import com.example.tlunet.extensions.categoriesCode
import com.example.tlunet.extensions.errStatus
import com.example.tlunet.extensions.subjects
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.http.FireStoreService.db
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.utils.getObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryDetailInteractor : CategoryDetailActivityContract.CategoryDetailInteractor {
    @SuppressLint("CheckResult")
    override fun getSubjects(categoryCode: String, callback: (status: String, list: List<Subjects>?) -> Unit) {
        val subjectDB = db.collection(subjects)
        val subjectQuery = subjectDB.whereEqualTo(categoriesCode,categoryCode)
        subjectQuery.getObservable<Subjects>()
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