package com.example.tlunet.navigation

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.tlunet.ui.category.CategoryActivity
import com.example.tlunet.ui.createaccount.CreateAccountActivity
import com.example.tlunet.ui.forgotPassword.ForgotPasswordActivity
import com.example.tlunet.ui.home.HomeActivity
import com.example.tlunet.ui.login.LoginActivity
import com.example.tlunet.ui.tabHome.HomeFragment

object Navigation {
    fun toHome(activity: Activity,clearBackStack: Boolean) {
        val intent = Intent(activity, HomeActivity::class.java)
        if (clearBackStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        activity.startActivity(intent)
    }
    fun toCategory(fragment: Fragment) {
        val intent = Intent(fragment.context, CategoryActivity::class.java)
    }
    fun toCreateAccount(activity: Activity) {
        val intent = Intent(activity, CreateAccountActivity::class.java)
        activity.startActivity(intent)
    }
    fun toForgotPassword(activity: Activity) {
        val intent = Intent(activity, ForgotPasswordActivity::class.java)
        activity.startActivity(intent)
    }
    fun toLogin(activity: Activity, clearStack: Boolean) {
        val intent = Intent(activity, LoginActivity::class.java)
        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        activity.startActivity(intent)
    }

}