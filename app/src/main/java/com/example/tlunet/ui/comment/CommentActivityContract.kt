package com.example.tlunet.ui.comment

import android.content.Intent
import com.mespitech.mvpbase.coremvp.BaseView

class CommentActivityContract {
    interface View : BaseView {
        fun onError(message : String)
        fun onSuccess(message: String)
    }
    interface Presenter {
        fun postComment(content : String, intent: Intent)
    }

    interface Interactor {
        fun addComment(content: String, author : String , code : String, callback : (status : String, message : String) -> Unit)
    }
}