package com.example.tlunet.ui.createaccount

import com.mespitech.mvpbase.coremvp.BaseView

class CreateAccountActivityContract {
    interface View : BaseView {
        fun errEmailInvalid()
        fun errPasswordInvalid()
        fun errPasswordNotMatches()
        fun onSuccess()
        fun onFailure(message: String)
    }

    interface Presenter {
        fun createAccount(email: String, password: String, confirmPassword: String)
    }

    interface Interactor {
        fun createAccount(
            email: String,
            password: String,
            callback: (status: String, message : String) -> Unit
        )
    }
}