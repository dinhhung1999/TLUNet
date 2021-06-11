package com.example.tlunet.ui.comment

import android.util.Log
import com.example.tlunet.R
import com.example.tlunet.extensions.*
import com.example.tlunet.http.FireStoreService.db
import java.util.*

class CommentActivityInteractor : CommentActivityContract.Interactor {
    override fun addComment(
        content: String,
        author: String,
        code: String,
        callback: (status: String, message: String) -> Unit
    ) {
        var createAt : Date = Date()

        val data = hashMapOf(
            authorKey to author,
            contentKey to content,
            subjectCode to code,
            createAtKey to createAt
        )


        val commentDB = db.collection(comments)
        commentDB.add(data)
            .addOnSuccessListener {
                callback(successStatus, "Thêm bình luận thành công" )
            }
            .addOnFailureListener { e ->
                callback(errStatus, "Thêm bình luận thất bại")
                Log.w("error: ", "Error adding document", e)
            }
    }
}