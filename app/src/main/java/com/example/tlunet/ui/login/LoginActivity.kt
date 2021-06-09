package com.example.tlunet.ui.login

import android.graphics.drawable.Drawable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.navigation.Navigation
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtEmail
import kotlinx.android.synthetic.main.activity_login.edtPassword
import kotlinx.android.synthetic.main.activity_login.imgVisiblePassword


class LoginActivity : BaseActivity<LoginPresenter>(), LoginActivityContract.View {
    private var visiblePassword = false

    override fun getLayoutId(): Int {
        return R.layout.activity_login;
    }
    override fun initPresenter(): LoginPresenter {
        return LoginPresenter()
    }
    override fun init() {

        imgVisiblePassword.setOnClickListener { makeVisiblePassword(!visiblePassword) }
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
    private fun makeVisiblePassword(visible: Boolean) {
        this.visiblePassword = visible
        if (visible) {
            edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imgVisiblePassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        } else {
            edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            imgVisiblePassword.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
        edtPassword.setSelection(edtPassword.text.length)
    }
    private fun onClickCreateAccount() {
//        tvRegister.isClickable = false
        Navigation.toCreateAccount(this)
    }
    private fun onClickForgotPassword() {
//        tvForgotPassword.isClickable = false
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