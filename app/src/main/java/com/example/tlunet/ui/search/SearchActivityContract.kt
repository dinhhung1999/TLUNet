package com.example.tlunet.ui.search

import com.example.tlunet.model.subjects.Subjects
import com.mespitech.mvpbase.coremvp.BaseView

class SearchActivityContract  {
    interface View : BaseView {
        fun fillSubjects(list : List<Subjects>)
        fun onError(message : String)
    }

    interface Presenter {
        fun searchSubject(subjectName : String)
    }

    interface Interactor {
        fun getSubject(subjectName: String, callback : (status : String, List<Subjects>?) -> Unit)
    }
}