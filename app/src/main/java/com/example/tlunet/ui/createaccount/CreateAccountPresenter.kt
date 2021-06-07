package com.example.tlunet.ui.createaccount

import com.example.tlunet.extensions.successStatus
import com.mespitech.mvpbase.coremvp.BasePresenter
import com.mespitech.mvpbase.utils.Validator

class CreateAccountPresenter : BasePresenter<CreateAccountActivityContract.View>(),
    CreateAccountActivityContract.Presenter {

    val interactor = CreateAccountInteractor()

    override fun createAccount(email: String, password: String, confirmPassword: String) {
        if (!validateEmail(email)) {
            mView?.errEmailInvalid()
            return
        }
        if (!validatePassword(password)) {
            mView?.errPasswordInvalid()
            return
        }
        if (!confirmPassword(password, confirmPassword)) {
            mView?.errPasswordNotMatches()
            return
        }
        mView?.showLoading()
        interactor.createAccount(email, password) { status, message ->
            mView?.dismissLoading()
            if(status == successStatus) {
                mView?.onSuccess()
            }
            else {
                mView?.onFailure(message)
            }

        }
    }

    private fun validateEmail(email: String): Boolean {
        return Validator.isValidEmail(email)
    }

    private fun validatePassword(password: String): Boolean {
        return  Validator.hasLength(password)
                && Validator.hasLowerCase(password)
                && Validator.hasUpperCase(password)
                && Validator.hasSymbol(password)
                && Validator.hasSpace(password)
                && Validator.hasNumber(password)
    }

    private fun confirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}