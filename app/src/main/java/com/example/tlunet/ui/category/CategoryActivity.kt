package com.example.tlunet.ui.category

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlunet.R
import com.example.tlunet.model.categories.Categories
import com.example.tlunet.navigation.Navigation
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : BaseActivity<CategoryPresenter>(), CategoryActivityContract.View {
    private lateinit var adapterCategory : CategoryAdapter2

    override fun getLayoutId(): Int {
        return R.layout.activity_category
    }

    override fun init() {
        navBar.setBackPressListener {
            finish()
        }
        mPresenter.fetchCategories()
        adapterCategory = CategoryAdapter2(this)
        rvCategory.adapter = adapterCategory
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        adapterCategory.setOnItemClickListener {  Navigation.toCategoryDetail(this,it.code!!,it.name!!) }

    }

    override fun initPresenter(): CategoryPresenter {
        return CategoryPresenter()
    }

    override fun fillCategories(data: List<Categories>) {
        adapterCategory.appendData(data)
    }

    override fun overrideStatusBar(): Drawable? {
        return getDrawable(R.drawable.gradient_theme)
    }

}