package com.example.tlunet.ui.forgotPassword

import com.example.tlunet.extensions.errStatus
import com.example.tlunet.extensions.successStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ForgotPasswordInteractor : ForgotPasswordActivityContract.Interactor {
    override fun resetPassword(email: String, callback: (status: String, message: String) -> Unit) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    callback(successStatus,"Đường dẫn đặt lại mật khẩu đã được gửi đến Email của bạn")
                }else {
                    callback(errStatus, "Đã xảy ra lỗi, vui lòng thử lại")
                }
            }
    }
}