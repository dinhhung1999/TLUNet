package com.example.tlunet.ui.login

import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService.auth


class LoginInteractor: LoginActivityContract.Interactor {
    override fun login(
        email: String,
        password: String,
        callback: (code: String, status: String, message: String, uid: String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(successCode, successStatus,"",task.result?.user?.uid)
                } else {
                    callback(errCode,errStatus, errLogin,"")
                }
            }
    }
}