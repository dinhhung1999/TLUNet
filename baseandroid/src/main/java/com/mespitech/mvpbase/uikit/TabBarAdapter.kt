package com.mespitech.mvpbase.uikit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import java.util.ArrayList

class TabBarAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addItem(fragment: Fragment) {
        this.fragments.add(fragment)
    }

    fun addItems(fragments: List<Fragment>) {
        this.fragments.addAll(fragments)
    }
}
