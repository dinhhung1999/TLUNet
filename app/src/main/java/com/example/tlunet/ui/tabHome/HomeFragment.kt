package com.example.tlunet.ui.tabHome

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlunet.R
import com.example.tlunet.extensions.*
import com.example.tlunet.model.categories.Categories
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.navigation.Navigation
import com.google.gson.Gson
import com.mespitech.mvpbase.coremvp.BaseFragment
import com.mespitech.mvpbase.coremvp.BaseView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeFragmentPresenter>(), HomeFragmentContract.View {
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
    private lateinit var adapterCNTT : SubjectAdapter
    private lateinit var adapterKT : SubjectAdapter
    private lateinit var adapterKTCK : SubjectAdapter
    private lateinit var adapterKTo : SubjectAdapter
    private lateinit var adapterCategory : CategoryAdapter

    override fun init() {
        mPresenter.fetchUserData()
        mPresenter.fetchCategories()
        mPresenter.fetchSubjects()
        ////recyle
        adapterCategory = CategoryAdapter(context!!)
        adapterCategory.setOnItemClickListener {
            Navigation.toCategoryDetail(context!!, it.code!!,it.name!!)
        }
        rvCategory.adapter = adapterCategory
        rvCategory.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        adapterCNTT = SubjectAdapter(context!!)
        adapterCNTT.setOnItemClickListener {
            Navigation.toSubjectDetail(context!!,it.code!!,it.name!!)
        }
        rvCNTT.adapter = adapterCNTT
        rvCNTT.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        rvKT.adapter = adapterCNTT //todo
        rvKT.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        rvKTCK.adapter = adapterCNTT //todo
        rvKTCK.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        rvKTo.adapter = adapterCNTT //todo
        rvKTo.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        tvMoreCategory.setOnClickListener {
            Navigation.toCategory(this)
        }
        tvMoreTech.setOnClickListener {
            Navigation.toCategoryDetail(context!!, CNTT,cnttName!!)
        }
        tvMoreEconomic.setOnClickListener {
            Navigation.toCategoryDetail(context!!, KT, ktName!!)
        }
        tvMoreAccount.setOnClickListener {
            Navigation.toCategoryDetail(context!!, KTo, ktoName!!)
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

    override fun fillSubjects(listCNTT: List<Subjects>?, listKT: List<Subjects>?, listKTCK: List<Subjects>?, listKTo: List<Subjects>?) {
        if(listCNTT!=null) {
            adapterCNTT.appendData(listCNTT)
        }
//        if(listKT!=null){
//            adapterKT.appendData(listKT)
//        }
//        if(listKTCK!=null){
//            adapterKTCK.appendData(listKTCK)
//        }
//        if(listKTo!=null){
//            adapterKTo.appendData(listKTo)
//        }
    }

    override fun fillCategories(listCategories: List<Categories>) {
        adapterCategory.appendData(listCategories)

    }


}