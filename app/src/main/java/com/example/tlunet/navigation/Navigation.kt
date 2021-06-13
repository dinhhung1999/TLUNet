package com.example.tlunet.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.tlunet.extensions.*
import com.example.tlunet.ui.category.CategoryActivity
import com.example.tlunet.ui.category.categorydetail.CategoryDetailActivity
import com.example.tlunet.ui.comment.CommentActivity
import com.example.tlunet.ui.createaccount.CreateAccountActivity
import com.example.tlunet.ui.forgotPassword.ForgotPasswordActivity
import com.example.tlunet.ui.home.HomeActivity
import com.example.tlunet.ui.login.LoginActivity
import com.example.tlunet.ui.postDocument.DocumentActivity
import com.example.tlunet.ui.postDocument.DocumentActivityContract
import com.example.tlunet.ui.search.SearchActivity
import com.example.tlunet.ui.subject.SubjectDetailActivity

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
        fragment.startActivity(intent)

    }
    fun toCreateAccount(activity: Activity) {
        val intent = Intent(activity, CreateAccountActivity::class.java)
        activity.startActivity(intent)
    }
    fun toForgotPassword(activity: Activity) {
        val intent = Intent(activity, ForgotPasswordActivity::class.java)
        activity.startActivity(intent)
    }
    fun toLogin(activity: Context, clearStack: Boolean) {
        val intent = Intent(activity, LoginActivity::class.java)
        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        activity.startActivity(intent)
    }

    fun toCategoryDetail(activity: Context, code: String, name: String) {
        val intent = Intent(activity, CategoryDetailActivity::class.java)
        intent.putExtra(categoriesCode, code)
        intent.putExtra(titleNav, name)
        activity.startActivity(intent)
    }
    fun toSubjectDetail(activity: Context, subjectCode: String, name: String) {
        val intent = Intent(activity, SubjectDetailActivity::class.java)
        intent.putExtra(code, subjectCode)
        intent.putExtra(titleNav, name)
        activity.startActivity(intent)
    }

    fun toCommentActivity(activity: Context,subjectCode: String) {
        val intent = Intent(activity, CommentActivity::class.java)
        intent.putExtra(code, subjectCode)
        activity.startActivity(intent)
    }

    fun toDocumentActivity(activity: Context) {
        val intent = Intent(activity, DocumentActivity::class.java)
        activity.startActivity(intent)
    }

    fun toSearchActivity(context: Context,subjectName: String) {
        val intent = Intent(context, SearchActivity::class.java)
        intent.putExtra(name, subjectName)
        context.startActivity(intent)
    }





}