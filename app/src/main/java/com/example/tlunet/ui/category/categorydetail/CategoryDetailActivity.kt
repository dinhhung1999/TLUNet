package com.example.tlunet.ui.category.categorydetail

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlunet.R
import com.example.tlunet.extensions.titleNav
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.navigation.Navigation
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_category_detail.*

class CategoryDetailActivity : BaseActivity<CategoryDetailPresenter>(),CategoryDetailActivityContract.View {
    private lateinit var adapter : SubjectAdapter2

    override fun getLayoutId(): Int {
        return R.layout.activity_category_detail
    }

    override fun init() {
        val title = intent.getStringExtra(titleNav)
        navBar.setTitle(title!!.toString())
        navBar.setBackPressListener {
            finish()
        }
        mPresenter.fetchSubjects(intent!!)
        adapter = SubjectAdapter2(this)
        adapter.setOnItemClickListener {
            Navigation.toSubjectDetail(this,it.code!!,it.name!!)
        }
        rvSubjects.adapter = adapter
        rvSubjects.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }

    override fun initPresenter(): CategoryDetailPresenter {
        return CategoryDetailPresenter()
    }

    override fun fillSubject(data: List<Subjects>?) {
        if (data != null) {
            adapter.appendData(data)
        }
    }

    override fun overrideStatusBar(): Drawable? {
        return getDrawable(R.drawable.gradient_theme)
    }
}