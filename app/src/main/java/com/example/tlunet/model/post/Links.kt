package com.example.tlunet.model.post

data class Links(
    val about: List<About>,
    val author: List<Author>,
    val collection: List<Collection>,
    val curies: List<Cury>,
    val predecessorVersion: List<PredecessorVersion>,
    val replies: List<Reply>,
    val self: List<Self>,
    val versionHistory: List<VersionHistory>,
    val wpAttachment: List<WpAttachment>,
    val wpFeaturedmedia: List<WpFeaturedmedia>,
    val wpTerm: List<WpTerm>
)