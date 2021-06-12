package com.example.tlunet.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.tlunet.R
import com.example.tlunet.extensions.emailKey
import com.example.tlunet.extensions.optionSubjectKey
import com.example.tlunet.extensions.users
import com.example.tlunet.model.User
import com.example.tlunet.model.subjects.SubjectOption
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class Preferences private constructor(val context: Context) {
    private val appName = context.getString(R.string.app_name)
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(appName, Context.MODE_PRIVATE)

    private val gson = Gson()

    companion object {
        private var instance: Preferences? = null

        @Throws(IllegalAccessException::class)
        fun getInstance(): Preferences {
            if (instance == null) {
                throw IllegalAccessException("Preferences must be initialized in Application class first.")
            }
            return instance as Preferences
        }

        fun init(context: Context) {
            instance = Preferences(context)
//            instance!!.saveFirstPhoneBook(false)
//            instance!!.saveFirstGPS(false)

        }
    }

    fun saveEmail(email: String) {

        sharedPreferences.edit().putString(emailKey, email).apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(emailKey, null)
    }

    fun saveUserData(user: User) {
        val json = gson.toJson(user)
        sharedPreferences.edit().putString(users, json).apply()
    }
    fun getUserData(): User? {
        val json = sharedPreferences.getString(users, null) ?: return null
        return gson.fromJson(json, User::class.java)
    }



}
