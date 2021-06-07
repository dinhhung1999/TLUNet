package com.example.tlunet.ui.login

import android.graphics.drawable.Drawable
import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.navigation.Navigation
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<LoginPresenter>(), LoginActivityContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_login;
    }
    override fun initPresenter(): LoginPresenter {
        return LoginPresenter()
    }
    override fun init() {
        btn_login.setOnClickListener {
            onClickLogin()
        }
        tvForgotPassword.setOnClickListener {
            onClickForgotPassword()
        }
        tvRegister.setOnClickListener {
            onClickCreateAccount()
        }

    }

    private fun onClickCreateAccount() {
        tvRegister.isClickable = false
        Navigation.toCreateAccount(this)
    }
    private fun onClickForgotPassword() {
        tvForgotPassword.isClickable = false
        Navigation.toForgotPassword(this)
    }
    override fun errPasswordToShort() {
        alert(getString(R.string.email_pass_null))
    }


    override fun errAuthenticateFailed(message: String) {
        alert(message)
    }

    override fun navigateHome() {
        Navigation.toHome(this, true)

    }

    private fun onClickLogin() {
        val phoneNumber = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        mPresenter.login(phoneNumber, password)
    }

    override fun overrideStatusBar(): Drawable? {
        return getDrawable(R.drawable.gradient_theme)
    }

}