package com.example.tlunet.ui.category

<<<<<<< HEAD
import com.mespitech.mvpbase.coremvp.BaseView

class CategoryActivityContract {
    interface View : BaseView
=======
import androidx.recyclerview.widget.AsyncListUtil
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
>>>>>>> 193b77027dcbb1fc1f4b0ce5d9c91f6c91cec630
}