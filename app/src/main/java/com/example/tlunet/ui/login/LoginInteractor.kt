package com.example.tlunet.ui.login

import com.example.tlunet.extensions.*
import com.google.firebase.auth.FirebaseAuth


class LoginInteractor: LoginActivityContract.Interactor {
    override fun login(
        email: String,
        password: String,
        callback: (code: String, status: String, message: String, uid: String?) -> Unit
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(successCode, successStatus,"",task.result?.user?.uid)
                } else {
                    callback(errCode,errStatus, errLogin,"")
                }
            }
    }
}