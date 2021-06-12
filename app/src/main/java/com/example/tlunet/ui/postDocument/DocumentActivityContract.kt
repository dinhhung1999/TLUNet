package com.example.tlunet.ui.postDocument

import android.content.Intent
import com.example.tlunet.model.subjects.SubjectOption
import com.mespitech.mvpbase.coremvp.BaseView

class DocumentActivityContract {
    interface View : BaseView {
        fun onError(message : String)
        fun onSuccess(message: String)
        fun fillSubjectOption(list : List<SubjectOption>)
    }
    interface Presenter {
        fun postDocument(subjectCode : String,documentUrl : String, intent: Intent)
        fun fetchSubjectOption()
        fun postFile()
    }

    interface Interactor {
        fun addDocuments(documentUrl: String, author : String , code : String, callback : (status : String, message : String) -> Unit)
        fun getSubjectOption(callback : (status : String, List<SubjectOption>?) -> Unit)
        fun postFile()
    }
}