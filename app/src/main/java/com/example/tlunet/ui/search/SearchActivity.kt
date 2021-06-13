package com.example.tlunet.ui.search

import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.extensions.name
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.navigation.Navigation
import com.example.tlunet.ui.category.categorydetail.SubjectAdapter2
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.navBar
import kotlinx.android.synthetic.main.activity_search.svSearch

class SearchActivity : BaseActivity<SearchActivityPresenter>(), SearchActivityContract.View {
    private lateinit var adapter : SubjectAdapter2

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun init() {
        navBar.setOnClickListener { finish() }
        val name = intent.getStringExtra(name).toString() ?: ""
        if(name.isNotEmpty()){
            svSearch.setQuery(name,false)
            mPresenter.searchSubject(name)
        }
        adapter = SubjectAdapter2(this)
        adapter.setOnItemClickListener {
            Navigation.toSubjectDetail(this,it.code!!,it.name!!)
        }
        rvSubjects.adapter = adapter
        rvSubjects.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mPresenter.searchSubject(query)
                svSearch.isFocusable = false;
                svSearch.isIconified = false;
                svSearch.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun initPresenter(): SearchActivityPresenter {
        return SearchActivityPresenter()
    }

    override fun fillSubjects(list: List<Subjects>) {
        if (list != null) {
            adapter.appendData(list)
        }
    }

    override fun onError(message: String) {
        alert(message)
    }

}