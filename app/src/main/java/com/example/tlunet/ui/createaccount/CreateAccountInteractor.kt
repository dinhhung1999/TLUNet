package com.example.tlunet.ui.createaccount

import com.example.tlunet.extensions.*
import com.google.firebase.auth.FirebaseAuth


class CreateAccountInteractor : CreateAccountActivityContract.Interactor {
    override fun createAccount(
        email: String,
        password: String,
        callback: ( status: String, message: String) -> Unit
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(successStatus,"")
                } else {
                    callback(errStatus,"Đăng ký tài khoản thất bại")
                }
            }

    }

}