package com.example.tlunet.ui.tabHome

import com.example.tlunet.model.categories.Categories
import com.example.tlunet.model.subjects.Subjects
import com.mespitech.mvpbase.coremvp.BaseView

class HomeFragmentContract {
    interface View : BaseView {
        fun onError(message : String)
        fun fillSubjects(listCNTT : List<Subjects>?,listKT : List<Subjects>?, listKTCK : List<Subjects>?, listKTo : List<Subjects>?)
        fun fillCategories(listCategories : List<Categories>)
    }

    interface Presenter {
        fun fetchCategories()
        fun fetchSubjects()
        fun searchSubject(subjectName : String)
    }
    interface Interactor {
        fun getCategories(callback: (status : String,List<Categories>?) -> Unit)
        fun getSubjects(callback: (status : String, List<Subjects>?) -> Unit)
        fun searchSubjects(subjectName: String,callback: (status : String, List<Subjects>?) -> Unit)
    }
}