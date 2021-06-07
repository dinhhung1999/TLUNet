package com.example.tlunet.http

import com.example.tlunet.model.post.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    fun getListPost() : Observable<List<Post>>
    @GET("posts")
    fun getPostDetail() : Observable<List<Post>>
    @GET("pages")
    fun getListPage() : Observable<List<Post>>
}