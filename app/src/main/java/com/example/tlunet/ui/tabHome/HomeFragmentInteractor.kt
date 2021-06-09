package com.example.tlunet.ui.tabHome

import android.annotation.SuppressLint
import android.util.Log
import com.example.tlunet.extensions.*
import com.example.tlunet.model.categories.Categories
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.utils.getObservable
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeFragmentInteractor : HomeFragmentContract.Interactor {
    val db = Firebase.firestore
    @SuppressLint("CheckResult")
    override fun getCategories(callback: (status: String, List<Categories>?) -> Unit) {
        val categoryDB = db.collection(categories)
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
//        categoryDB.get().addOnSuccessListener { documents ->
//            if (documents != null) {
//                val  data = mutableListOf<Categories>()
//                for (document in documents.iterator()) {
//                   val category = document.toObject(Categories::class.java)
//                    data.add(category)
//                }
//                callback(successStatus, data)
//            } else {
//                callback(errStatus, null)
//            }
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//             callback(errStatus, null)
//        }

    }

    @SuppressLint("CheckResult")
    override fun getSubjects(callback: (status: String, List<Subjects>?) -> Unit) {
        val subjectDB = db.collection(subjects)
        subjectDB.getObservable<Subjects>()
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

//        subjectDB.get().addOnSuccessListener { documents ->
//            if (documents != null) {
//                val  data = mutableListOf<Subjects>()
//                for (document in documents.iterator()) {
//                    val subject = document.toObject(Subjects::class.java)
//                    data.add(subject)
//                }
//                callback(successStatus, data)
//            } else {
//                callback(errStatus, null)
//            }
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//            callback(errStatus, null)
//        }
    }

    @SuppressLint("CheckResult")
    override fun searchSubjects(subjectName: String, callback: (status: String, List<Subjects>?) -> Unit) {
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

//        subjectDB.whereEqualTo(name,subjectName).get().addOnSuccessListener { documents ->
//            if (documents != null) {
//                val  data = mutableListOf<Subjects>()
//                for (document in documents.iterator()) {
//                    val subject = document.toObject(Subjects::class.java)
//                    data.add(subject)
//                }
//                callback(successStatus, data)
//            } else {
//                callback(errStatus, null)
//            }
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//            callback(errStatus, null)
//        }
    }
}