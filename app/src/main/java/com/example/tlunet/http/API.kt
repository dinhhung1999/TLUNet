package com.example.tlunet.http

object API {
    val apiService: APIService = ApiClient().getService(APIService::class.java)
}