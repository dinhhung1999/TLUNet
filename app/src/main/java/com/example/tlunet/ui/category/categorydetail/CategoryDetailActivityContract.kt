package com.example.tlunet.ui.category.categorydetail

import android.content.Intent
import com.example.tlunet.model.subjects.Subjects
import com.mespitech.mvpbase.coremvp.BaseView

class CategoryDetailActivityContract {
    interface View : BaseView {
        fun fillSubject(data : List<Subjects>?)
    }

    interface CategoryDetailPresenter {
        fun fetchSubjects(intent: Intent)
    }

    interface CategoryDetailInteractor {
        fun getSubjects(categoryCode : String, callback : (status: String, list : List<Subjects>?) -> Unit)
    }
}