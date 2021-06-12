package com.example.tlunet.ui.createaccount

import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService.auth
import com.example.tlunet.http.FireStoreService.db


class CreateAccountInteractor : CreateAccountActivityContract.Interactor {
    override fun createAccount(
        email: String,
        password: String,
        callback: ( status: String, message: String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    setUserData(email)
                    callback(successStatus,"")
                } else {
                    callback(errStatus,"Đăng ký tài khoản thất bại")
                }
            }

    }

    private fun setUserData(email : String) {
        db.collection(users).document(email).set({
            emailKey to email
            fullnameKey to ""
            roleKey to 0
        })
    }

}