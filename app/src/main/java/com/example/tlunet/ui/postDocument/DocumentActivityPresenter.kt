package com.example.tlunet.ui.postDocument

import android.content.Intent
import com.example.tlunet.extensions.code
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.utils.Preferences
import com.mespitech.mvpbase.coremvp.BasePresenter

class DocumentActivityPresenter : BasePresenter<DocumentActivityContract.View>() , DocumentActivityContract.Presenter {
    val interactor = DocumentActivityInteractor()

    override fun postDocument(subjectCode : String,documentUrl: String, intent: Intent) {
        mView?.showLoading()
        val user = Preferences.getInstance().getUserData() ?: return
        interactor.addDocuments(documentUrl,user.fullname ?: user.email!!,subjectCode) {status, message ->
            mView?.dismissLoading()
            if(status == successStatus) {
                mView?.onSuccess(message)
            }else {
                mView?.onError(message)
            }
        }
    }

    override fun fetchSubjectOption() {
        mView?.showLoading()
        interactor.getSubjectOption{status, list ->
            mView?.dismissLoading()
            if(status == successStatus) {
                mView?.fillSubjectOption(list!!)
            }
        }
    }

    override fun postFile() {
    }
}