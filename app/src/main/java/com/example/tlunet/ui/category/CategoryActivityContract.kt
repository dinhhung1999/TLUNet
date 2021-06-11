package com.example.tlunet.ui.category

import com.example.tlunet.model.categories.Categories
import com.mespitech.mvpbase.coremvp.BaseView


class CategoryActivityContract {
    interface View : BaseView {
        fun fillCategories(data : List<Categories>)
    }

    interface Presenter {
        fun fetchCategories()
    }
    interface Interactor {
        fun getCategories(callback: (status : String,List<Categories>?) -> Unit)
    }

}