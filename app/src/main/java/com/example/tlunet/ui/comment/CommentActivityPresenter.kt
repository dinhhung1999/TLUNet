package com.example.tlunet.ui.comment

import android.content.Intent
import com.example.tlunet.extensions.code
import com.example.tlunet.extensions.successStatus
import com.example.tlunet.utils.Preferences
import com.mespitech.mvpbase.coremvp.BasePresenter

class CommentActivityPresenter : BasePresenter<CommentActivityContract.View>(), CommentActivityContract.Presenter {

    val interactor = CommentActivityInteractor()
    override fun postComment(content: String, intent: Intent) {
        mView?.showLoading()
        val email = Preferences.getInstance().getEmail()
        val code = intent.getStringExtra(code)
        interactor.addComment(content,email!!,code!!) {status, message ->
            mView?.dismissLoading()
            if(status == successStatus) {
                mView?.onSuccess(message)
            }else {
                mView?.onError(message)
            }
        }

    }
}