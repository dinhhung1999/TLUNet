package com.example.tlunet.ui.subject

import android.content.Intent
import com.example.tlunet.model.comments.Comment
import com.example.tlunet.model.documents.Document
import com.example.tlunet.model.subjects.Subjects
import com.mespitech.mvpbase.coremvp.BaseView

class SubjectDetailContract {
    interface View : BaseView {
        fun fillSubject(subject: Subjects?)
        fun fillDocuments(listDocuments: List<Document>?)
        fun fillComments(listComments: List<Comment>?)
    }

    interface Presenter {
        fun fetchSubject(intent: Intent)
        fun fetchDocuments(intent: Intent)
        fun fetchComments(intent: Intent)
    }

    interface Interactor {
        fun getSubjects(codeSubject : String, callback : (status : String, List<Subjects>?) -> Unit)
        fun getDocuments(codeSubject : String, callback : (status : String,  List<Document>?) -> Unit)
        fun getComments(codeSubject : String,callback:  (status : String, List<Comment>?) -> Unit)
    }
}