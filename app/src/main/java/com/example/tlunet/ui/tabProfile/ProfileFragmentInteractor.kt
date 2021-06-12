package com.example.tlunet.ui.tabProfile

import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService

class ProfileFragmentInteractor : ProfileFragmentContract.Interactor {
    override fun logout(callback: (status: String) -> Unit) {
        FireStoreService.auth.signOut()
        callback(successStatus)
    }
}