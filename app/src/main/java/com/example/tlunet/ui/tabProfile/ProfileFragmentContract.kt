package com.example.tlunet.ui.tabProfile

import com.mespitech.mvpbase.coremvp.BaseView

class ProfileFragmentContract {
    interface View : BaseView {
        fun onSuccess()
        fun onError()
    }

    interface Presenter {
        fun logout()
    }
    interface Interactor {
        fun logout(callback : (status: String) ->Unit)
    }
}