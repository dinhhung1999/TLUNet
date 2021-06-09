package com.example.tlunet.model.documents

data class Document(
    val createTime: String,
    val fields: Fields,
    val name: String,
    val updateTime: String
)