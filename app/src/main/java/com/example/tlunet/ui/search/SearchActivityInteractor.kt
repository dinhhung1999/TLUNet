package com.example.tlunet.ui.search

import android.annotation.SuppressLint
import android.util.Log
import com.example.tlunet.extensions.errStatus
import com.example.tlunet.extensions.name
import com.example.tlunet.extensions.subjects
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.http.FireStoreService.db
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.utils.getObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchActivityInteractor : SearchActivityContract.Interactor {
    @SuppressLint("CheckResult")
    override fun getSubject(subjectName: String, callback: (status: String, List<Subjects>?) -> Unit) {
        val subjectDB = db.collection(subjects)
        val subjectQuery = subjectDB.whereEqualTo(name,subjectName)
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