package com.example.tlunet.navigation

import android.app.Activity
import android.content.Intent
import com.example.tlunet.ui.home.HomeActivity

object Navigation {
    fun toHome(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }
}