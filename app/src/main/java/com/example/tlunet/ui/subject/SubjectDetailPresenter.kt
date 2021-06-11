package com.example.tlunet.ui.subject

import android.content.Intent
import android.util.Log
import com.example.tlunet.extensions.code
import com.example.tlunet.extensions.successStatus
import com.mespitech.mvpbase.coremvp.BasePresenter

class SubjectDetailPresenter : BasePresenter<SubjectDetailContract.View>(), SubjectDetailContract.Presenter {
    val interactor = SubjectDetailInteractor()
    var subjectCode : String = ""
    override fun fetchSubject(intent: Intent) {
        mView?.showLoading()
        val code = intent.getStringExtra(code)
        subjectCode = code.toString()
        interactor.getSubjects(code.toString()) {status, list ->
            mView?.dismissLoading()
            if(status == successStatus ){
                    mView?.fillSubject(list?.get(0))
                }
                else {
                    Log.e("error", status)
                }
        }
    }

    override fun fetchDocuments(intent: Intent) {
        mView?.showLoading()
        val code = intent.getStringExtra(code)
        subjectCode = code.toString()
        interactor.getDocuments(code.toString()) {status, list ->
            mView?.dismissLoading()
            if(status == successStatus ){
                mView?.fillDocuments(list!!)
            }
            else {
                Log.e("error", status)
            }
        }
    }

    override fun fetchComments(intent: Intent) {
        mView?.showLoading()
        val code = intent.getStringExtra(code)
        subjectCode = code.toString()
        interactor.getComments(code.toString()) {status, list ->
            mView?.dismissLoading()
            if(status == successStatus ){
                mView?.fillComments(list!!)
            }
            else {
                Log.e("error", status)
            }
        }
    }
}