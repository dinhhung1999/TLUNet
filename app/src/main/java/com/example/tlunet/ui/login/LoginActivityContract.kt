package com.example.tlunet.ui.login

import android.os.Bundle
import com.mespitech.mvpbase.coremvp.BaseView

class LoginActivityContract {
    interface View : BaseView {
        fun errPasswordToShort()
        fun errAuthenticateFailed(message: String)
        fun navigateHome()

    }

    interface Presenter {
        fun login(phoneNumber: String, password: String)
    }

    interface Interactor {
        fun login(email: String, password: String, callback: (code: String, status: String, message: String, uid: String?) -> Unit)
    }
}