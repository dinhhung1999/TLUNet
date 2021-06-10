package com.example.tlunet.ui.createaccount

import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService.auth


class CreateAccountInteractor : CreateAccountActivityContract.Interactor {
    override fun createAccount(
        email: String,
        password: String,
        callback: ( status: String, message: String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(successStatus,"")
                } else {
                    callback(errStatus,"Đăng ký tài khoản thất bại")
                }
            }

    }

}