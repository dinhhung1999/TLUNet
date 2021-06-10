package com.example.tlunet.ui.forgotPassword

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.navigation.Navigation
import com.example.tlunet.view.DoSuccessfulDialog
import com.example.tlunet.view.RegisterSuccessfulDialog
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.edtEmail
import kotlinx.android.synthetic.main.activity_forgot_password.navBar

class ForgotPasswordActivity : BaseActivity<ForgotPasswordPresenter>(),ForgotPasswordActivityContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_forgot_password
    }

    override fun init() {
        navBar.setBackPressListener {
            finish()
        }
        btnForgotPassword.setOnClickListener {
            onClickForgotPassword()
        }
    }
    private fun onClickForgotPassword() {
        val email = edtEmail.text.toString()
        mPresenter.resetPassword(email)
    }

    override fun onResume() {
        super.onResume()
        edtEmail.setText("")
//        btnForgotPassword.isClickable = true
    }

    override fun initPresenter(): ForgotPasswordPresenter {
        return ForgotPasswordPresenter()
    }

    override fun errEmail() {
        alert(getString(R.string.invalidEmail))
//        btnForgotPassword.isClickable = true
    }

    override fun onError(message: String) {
        alert(message)
//        btnForgotPassword.isClickable = true
    }

    override fun onSuccess(message: String) {
        DoSuccessfulDialog(this,message) {
            Navigation.toLogin(this,true)
        }.show()
//        btnForgotPassword.isClickable = true
    }
    override fun overrideStatusBar(): Drawable? {
        return getDrawable(R.drawable.gradient_theme)
    }
}