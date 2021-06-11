package com.example.tlunet.model.documents

import java.io.Serializable

data class Document(
    val author: String? ="",
    val documentUrl: String? = "",
    val subjectCode: String? =""
) : Serializable