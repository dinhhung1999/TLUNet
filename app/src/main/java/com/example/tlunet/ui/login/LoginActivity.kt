package com.example.tlunet.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tlunet.R
import com.example.tlunet.navigation.Navigation
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            Navigation.toHome(this)
        }
    }
}