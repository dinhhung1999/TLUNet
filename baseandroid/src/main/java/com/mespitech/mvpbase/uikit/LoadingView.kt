package com.mespitech.mvpbase.uikit

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mespitech.mvpbase.R
import kotlinx.android.synthetic.main.loading_view.*

class LoadingView(context: Context) :
    Dialog(context, R.style.LoadingView) {
    init {
        try {
            setContentView(R.layout.loading_view)
            val a: Animation = AnimationUtils.loadAnimation(context, R.anim.animation)
            a.reset()
            tv.clearAnimation()
            tv.startAnimation(a)
            window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }catch (e: Exception){
        }
    }
}
