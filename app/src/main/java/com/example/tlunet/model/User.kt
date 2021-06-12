package com.example.tlunet.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
        var email: String ? = "",
        var fullname: String ? = "",
        var role : Int ? = 0,
) : Serializable