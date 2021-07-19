package com.example.tlunet.ui.login

import com.example.tlunet.extensions.successCode
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.utils.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.mespitech.mvpbase.coremvp.BasePresenter

class LoginPresenter : BasePresenter<LoginActivityContract.View>(),
    LoginActivityContract.Presenter {
    var Uid : String? = ""
    val interactor = LoginInteractor()

    override fun login(email: String, password: String) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mView?.errEmail()
            return
        }
        if (password.length < 4) {
            mView?.errPasswordToShort()
            return
        }
        mView?.showLoading()

        interactor.login(email,password) { code, status, message, uid ->
            mView?.dismissLoading()
            if(status == successStatus && code == successCode) {
                Uid = uid
                Preferences.getInstance().saveEmail(email)
                mView?.navigateHome()
            }
            else{
                mView?.errAuthenticateFailed(message)
            }
        }
    }
}