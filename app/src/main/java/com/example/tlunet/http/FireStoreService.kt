package com.example.tlunet.http

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FireStoreService {
    val  db = Firebase.firestore
    val  auth = FirebaseAuth.getInstance()
}