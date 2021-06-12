package com.example.tlunet.ui.comment

import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.navigation.Navigation
import com.example.tlunet.view.DoSuccessfulDialog
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : BaseActivity<CommentActivityPresenter>(), CommentActivityContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_comment
    }

    override fun init() {
        navBar.setOnClickListener { finish() }

        btn_send.setOnClickListener {
            if(edtComment.text.toString().trim().isNotEmpty()){
                mPresenter.postComment(edtComment.text.toString(),intent)
            }
        }
    }

    override fun initPresenter(): CommentActivityPresenter {
        return CommentActivityPresenter()
    }

    override fun onError(message: String) {
        alert(message)
    }

    override fun onSuccess(message: String) {
        DoSuccessfulDialog(this,message) {
            finish()
        }.show()
    }
}