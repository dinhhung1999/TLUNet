package com.example.tlunet.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.tlunet.R
import com.example.tlunet.ui.tabHome.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
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
            HomeFragment(),
            HomeFragment(),
            HomeFragment()
        )
        vpHome.adapter = HomePagerAdapter(supportFragmentManager, fragments)
        vpHome.offscreenPageLimit = 4

        navBottom.setOnNavigationItemReselectedListener {
            var fragment: Fragment? = null
            when(it.itemId){
                R.id.itemHome ->  selectTab(0)
//                R.id.itemHome -> fragment = HomeFragment()

                R.id.itemSearch -> selectTab(1)
                R.id.itemWishlist -> selectTab(2)
                R.id.itemProfile -> selectTab(3)
            }
//            loadFragment(fragment);
        }

        selectTab(0)
    }
//    private fun loadFragment(fragment: Fragment?): Boolean {
//        if (fragment != null) {
//            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//            ft.replace(R.id.vpHome, fragment)
//            ft.commit()
//            return true
//        }
//        return false
//    }

    private fun selectTab(index: Int) {
//
//        navBottom.forEachIndexed { i, tabBarItem ->
////
//        }
        vpHome.setCurrentItem(index, false)
    }


}