package com.example.tlunet.ui.tabHome

import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlunet.R
import com.example.tlunet.extensions.*
import com.example.tlunet.model.categories.Categories
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.navigation.Navigation
import com.mespitech.mvpbase.coremvp.BaseFragment
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.svSearch


class HomeFragment : BaseFragment<HomeFragmentPresenter>(), HomeFragmentContract.View {
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
    private lateinit var adapterCNTT : SubjectAdapter
    private lateinit var adapterKT : SubjectAdapter
    private lateinit var adapterKTCK : SubjectAdapter
    private lateinit var adapterCategory : CategoryAdapter

    override fun init() {
        mPresenter.fetchUserData()
        mPresenter.fetchCategories()
        mPresenter.fetchSubjects()
        ////recyle
        adapterCategory = CategoryAdapter(context!!)
        adapterCategory.setOnItemClickListener {
            Navigation.toCategoryDetail(context!!, it.code!!, it.name!!)
        }
        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Navigation.toSearchActivity(context!!,query)
                svSearch.setQuery("",false)
                svSearch.isFocusable = false;
                svSearch.isIconified = false;
                svSearch.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        rvCategory.adapter = adapterCategory
        rvCategory.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        adapterCNTT = SubjectAdapter(context!!)
        adapterCNTT.setOnItemClickListener {
            Navigation.toSubjectDetail(context!!, it.code!!, it.name!!)
        }
        rvCNTT.adapter = adapterCNTT
        rvCNTT.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapterKT = SubjectAdapter(context!!)
        adapterKT.setOnItemClickListener {
            Navigation.toSubjectDetail(context!!, it.code!!, it.name!!)
        }
        rvKT.adapter = adapterKT
        rvKT.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        adapterKTCK = SubjectAdapter(context!!)
        adapterKTCK.setOnItemClickListener {
            Navigation.toSubjectDetail(context!!, it.code!!, it.name!!)
        }
        rvKTCK.adapter = adapterKTCK
        rvKTCK.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        tvMoreCategory.setOnClickListener {
            Navigation.toCategory(this)
        }
        tvMoreTech.setOnClickListener {
            Navigation.toCategoryDetail(context!!, CNTT, cnttName!!)
        }
        tvMoreEconomic.setOnClickListener {
            Navigation.toCategoryDetail(context!!, KT, ktName!!)
        }

        tvMoreMechanical.setOnClickListener {
            Navigation.toCategoryDetail(context!!, KTCK, ktckName!!)
        }
    }

    override fun initPresenter(): HomeFragmentPresenter {
        return HomeFragmentPresenter()
    }

    override fun onError(message: String) {
        alert(message)
    }

    override fun fillSubjects(
        listCNTT: List<Subjects>?,
        listKT: List<Subjects>?,
        listKTCK: List<Subjects>?
    ) {
        if(listCNTT!=null) {
            adapterCNTT.appendData(listCNTT)
        }
        if(listKT!=null){
            adapterKT.appendData(listKT)
        }
        if(listKTCK!=null){
            adapterKTCK.appendData(listKTCK)
        }

    }

    override fun fillCategories(listCategories: List<Categories>) {
        adapterCategory.appendData(listCategories)

    }


}