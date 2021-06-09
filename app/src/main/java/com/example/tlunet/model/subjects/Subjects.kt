package com.example.tlunet.model.subjects

import java.io.Serializable

data class Subjects(
    val categoriesCode: String ? = "",
    val categoriesName: String ? = "",
    val code: String ? = "",
    val credit: Int ? = 0,
    val imgUrl: String ? = "",
    val lecturers: String ? = "",
    val lesson: String ? = "",
    val name: String ? = "",
    val referenceDocuments: List<String>? = null
) : Serializable