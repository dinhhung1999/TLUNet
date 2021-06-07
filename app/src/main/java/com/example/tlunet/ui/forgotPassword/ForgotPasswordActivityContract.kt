package com.example.tlunet.ui.forgotPassword

import com.mespitech.mvpbase.coremvp.BaseView

class ForgotPasswordActivityContract {
    interface View : BaseView {
        fun errEmail()
        fun onError(message: String)
        fun onSuccess(message: String)

    }

    interface Presenter {
        fun resetPassword(email: String)
    }

    interface Interactor {
        fun resetPassword(email: String, callback: (status: String, message: String) -> Unit)
    }
}