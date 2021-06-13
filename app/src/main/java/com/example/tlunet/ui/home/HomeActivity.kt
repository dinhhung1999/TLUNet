package com.example.tlunet.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tlunet.R
import com.example.tlunet.ui.tabHome.HomeFragment
import com.example.tlunet.ui.tabProfile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


open class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navigationBarColor = window.navigationBarColor
        val background = resources.getDrawable(R.drawable.gradient_theme, null)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        window.setBackgroundDrawable(background)
        window.navigationBarColor = navigationBarColor
        val fragments = listOf(
                HomeFragment(),
                ProfileFragment()
        )
        vpHome.adapter = HomePagerAdapter(supportFragmentManager, fragments)
        vpHome.offscreenPageLimit = 2


        navBottom.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> selectTab(0)
//                R.id.itemSearch -> selectTab(1)
                R.id.itemProfile -> selectTab(1)
                else -> selectTab(0)
            }
        })

    }


    private fun selectTab(index: Int): Boolean {
        vpHome.setCurrentItem(index, false)
        return true
    }



}