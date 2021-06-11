package com.example.tlunet.ui.subject

import android.annotation.SuppressLint
import android.util.Log
import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService
import com.example.tlunet.model.comments.Comment
import com.example.tlunet.model.documents.Document
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.utils.getObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SubjectDetailInteractor : SubjectDetailContract.Interactor {
    @SuppressLint("CheckResult")
    override fun getSubjects(codeSubject: String, callback: (status: String, List<Subjects>?) -> Unit) {
        val subjectDB = FireStoreService.db.collection(subjects)
        val subjectQuery = subjectDB.whereEqualTo(code,codeSubject)
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

    @SuppressLint("CheckResult")
    override fun getDocuments(
        codeSubject: String,
        callback: (status: String, List<Document>?) -> Unit
    ) {
        val documentDB = FireStoreService.db.collection(documents)
        val documentQuery = documentDB.whereEqualTo( subjectCode,codeSubject,)
        documentQuery.getObservable<Document>()
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

    @SuppressLint("CheckResult")
    override fun getComments(
        codeSubject: String,
        callback: (status: String, List<Comment>?) -> Unit
    ) {
        val commentDB = FireStoreService.db.collection(comments)
        val commentQuery = commentDB.whereEqualTo(subjectCode,codeSubject).orderBy(createAtKey)
        commentQuery.getObservable<Comment>()
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