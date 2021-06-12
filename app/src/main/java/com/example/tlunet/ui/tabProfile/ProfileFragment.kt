package com.example.tlunet.ui.tabProfile

import android.view.View
import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.navigation.Navigation
import com.example.tlunet.utils.Preferences
import com.mespitech.mvpbase.coremvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.btnShare


class ProfileFragment : BaseFragment<ProfileFragmentPresenter>(), ProfileFragmentContract.View {
    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun init() {
        val userData = Preferences.getInstance().getUserData() ?: return
        tvEmail.text = userData.email
        if(userData.role == 1) {
            btnShare.visibility = View.VISIBLE
            btnShare.isClickable = true
            btnShare.isEnabled = true
            btnShare.setOnClickListener {
                Navigation.toDocumentActivity(context!!)
            }
        }
        else {
            btnShare.visibility = View.GONE
            btnShare.isClickable = false
            btnShare.isEnabled = false
        }
        itLogout.setOnClickListener {
            mPresenter.logout()
        }
        itLanguage.setOnClickListener {
            alert("Tính năng đang phát triển")
        }
    }

    override fun initPresenter(): ProfileFragmentPresenter {
        return ProfileFragmentPresenter()
    }

    override fun onSuccess() {
        Navigation.toLogin(context!!,true)
    }

    override fun onError() {
        alert("Đã có lỗi xảy ra")
    }
}