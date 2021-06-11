package com.example.tlunet.model.comments

import java.io.Serializable
import java.util.*

data class Comment(
        val author: String? ="",
        val content: String? ="",
        val createAt: Date?= null,
        val subjectCode: String?=""
) : Serializable