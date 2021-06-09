package com.example.tlunet.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tlunet.R
import com.example.tlunet.navigation.Navigation


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       try {
           setContentView(R.layout.activity_splash)
               Handler().postDelayed({
                       Navigation.toLogin(this, true)
               }, 1000)
       }catch (e: Exception){
           Navigation.toLogin(this, true)
       }
//        Navigation.toHome(this, true)
    }
}
