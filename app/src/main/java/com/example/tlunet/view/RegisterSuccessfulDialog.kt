package com.example.tlunet.view

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Message
import android.view.View
import com.example.tlunet.R
import kotlinx.android.synthetic.main.dialog_register_successful.*

class RegisterSuccessfulDialog(context: Context, val f: () -> Unit) :
    Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_register_successful)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        imgClose.setOnClickListener {
            cancel()
            f()
        }

    }
}

class DoSuccessfulDialog(context: Context, val title: String?, val f: () -> Unit) :
    Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_register_successful)
        setCancelable(false)
        setText()
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        imgClose.setOnClickListener {
            cancel()
            f()
        }


    }
    private fun setText(){
        if (title != null){
            tvTitle.text = title
        }else{
            tvTitle.visibility = View.GONE
        }

    }
}

class NotificationDialog(context: Context, val title: String?, val message: String?, val btnTitle: String?, val imageNoti: Drawable?, val f: () -> Unit) :
    Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_register_successful)
        setCancelable(false)
        setText()
        if (imageNoti != null){
            imgAvatar.setImageDrawable(imageNoti)
        }
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        imgClose.setOnClickListener {
            cancel()
            f()
        }


    }
    private fun setText(){
        if (title != null){
            tvTitle.text = title
        }else{
            tvTitle.visibility = View.GONE
        }

    }
}