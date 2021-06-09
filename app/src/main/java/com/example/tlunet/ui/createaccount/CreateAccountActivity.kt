package com.example.tlunet.ui.createaccount

import android.graphics.drawable.Drawable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.navigation.Navigation
import com.example.tlunet.view.RegisterSuccessfulDialog
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_create_account.*


class CreateAccountActivity : BaseActivity<CreateAccountPresenter>(),
    CreateAccountActivityContract.View {

    private var visiblePassword = false
    private var visibleConfirmPassword = false

    override fun getLayoutId(): Int {
        return R.layout.activity_create_account
    }

    override fun init() {
        navBar.setBackPressListener {
            finish()
        }

        imgVisiblePassword.setOnClickListener { makeVisiblePassword(!visiblePassword) }
        imgVisibleConfirmPassword.setOnClickListener { makeVisibleConfirmPassword(!visibleConfirmPassword) }
        btnCreateAccount.setOnClickListener {
//            if (cbCaptcha.isChecked){
                onClickCreateAccount()
//            }else{
//                alert(getString(R.string.captcha_yet))
//            }
        }

        makeVisiblePassword(visiblePassword)
        makeVisibleConfirmPassword(visibleConfirmPassword)
        // set Captcha

    }

    override fun onResume() {
        super.onResume()
        edtEmail.setText("")
        edtPassword.setText("")
        edtConfirmPassword.setText("")
//        btnCreateAccount.isClickable = true
    }

    override fun initPresenter(): CreateAccountPresenter {
        return CreateAccountPresenter()
    }



    override fun errEmailInvalid() {
        alert(getString(R.string.invalidEmail))
//        btnCreateAccount.isClickable = true

    }

    override fun errPasswordInvalid() {
        alert(getString(R.string.invalidPassword))
//        btnCreateAccount.isClickable = true
    }

    override fun errPasswordNotMatches() {
        alert(getString(R.string.err_passwword_not_too))
//        btnCreateAccount.isClickable = true
    }

    private fun onClickCreateAccount() {
//        btnCreateAccount.isClickable = false
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        val confirmPassword = edtConfirmPassword.text.toString()
        mPresenter.createAccount(email, password, confirmPassword)
    }



    override fun onSuccess() {
        RegisterSuccessfulDialog(this) {
            Navigation.toLogin(this,true)
        }.show()
//        btnCreateAccount.isClickable = true

    }

    override fun onFailure(message: String) {
        alert(message)
//        btnCreateAccount.isClickable = true

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

    private fun makeVisibleConfirmPassword(visible: Boolean) {
        this.visibleConfirmPassword = visible
        if (visible) {
            edtConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imgVisibleConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        } else {
            edtConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            imgVisibleConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
        edtConfirmPassword.setSelection(edtConfirmPassword.text.length)
    }

    override fun overrideStatusBar(): Drawable? {
        return getDrawable(R.drawable.gradient_theme)
    }
}
