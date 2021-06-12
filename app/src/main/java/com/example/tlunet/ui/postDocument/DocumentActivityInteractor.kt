package com.example.tlunet.ui.postDocument

import android.annotation.SuppressLint
import android.util.Log
import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService
import com.example.tlunet.model.subjects.SubjectOption
import com.example.tlunet.utils.Preferences
import com.example.tlunet.utils.getObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DocumentActivityInteractor : DocumentActivityContract.Interactor {


    override fun addDocuments(documentUrl: String, author: String, code: String, callback: (status: String, message: String) -> Unit) {
        val data = hashMapOf(
                authorKey to author,
                documentUrlKey to documentUrl,
                subjectCode to code
        )


        val documentDB = FireStoreService.db.collection(documents)
        documentDB.add(data)
                .addOnSuccessListener {
                    callback(successStatus, "Thêm tài liệu thành công" )
                }
                .addOnFailureListener { e ->
                    callback(errStatus, "Thêm tài liệu thất bại")
                    Log.w("error: ", "Error adding document", e)
                }
    }

    @SuppressLint("CheckResult")
    override fun getSubjectOption(callback: (status: String, List<SubjectOption>?) -> Unit) {
        val subjectDB = FireStoreService.db.collection(subjects)
        subjectDB.getObservable<SubjectOption>()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ documents ->
                    if (documents != null) {
                        callback(successStatus,documents)
                    } else {
                        callback(errStatus,null)
                    }
                }, { exception ->
                    Log.e("firebase", "Error getting data", exception)
                    callback(errStatus,null)
                })

    }

    override fun postFile() {
        TODO("Not yet implemented")
    }
}