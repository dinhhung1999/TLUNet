package com.example.tlunet.ui.forgotPassword

import com.example.tlunet.extensions.successStatus
import com.mespitech.mvpbase.coremvp.BasePresenter
import com.mespitech.mvpbase.utils.Validator

class ForgotPasswordPresenter : BasePresenter<ForgotPasswordActivityContract.View>(), ForgotPasswordActivityContract.Presenter {
    val interactor = ForgotPasswordInteractor()
    override fun resetPassword(email: String) {
        if (!validateEmail(email)) {
            mView?.errEmail()
            return
        }
        mView?.showLoading()
        interactor.resetPassword(email) {status, message ->
            mView?.dismissLoading()
            if(status == successStatus){
                mView?.onSuccess(message)
            }
            else{
                mView?.onError(message)
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return Validator.isValidEmail(email)
    }
}