package com.example.tlunet.http
import com.google.gson.annotations.SerializedName

class ServiceResult<T> {
    @SerializedName("code")
    var code = ""

    @SerializedName("status")
    var status = ""

    @SerializedName("message")
    var message = ""

    @SerializedName("total_page")
    var total_page = 1

    @SerializedName("data")
    var result: T? = null
}