package com.example.tlunet.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

//import com.google.firebase.crashlytics.FirebaseCrashlytics

fun Activity.alert(message: String) {
    alert(null, message, "OK")
}

fun Activity.alert(message: String, function: (DialogInterface) -> Unit) {
    alert(null, message, "OK", false, function)
}

fun Activity.alert(title: String?, message: String) {
    alert(title, message, "OK")
}

fun Activity.alert(title: String?, message: String, buttonText: String) {
    alert(title, message, buttonText, true) { it.cancel() }
}

fun Activity.alert(
    title: String? = null,
    message: String = "",
    buttonText: String = "OK",
    cancelable: Boolean = true,
    function: (DialogInterface) -> Unit
) {
    try {
        val builder = AlertDialog.Builder(this)
        if (title != null) {
            builder.setTitle(title)
        }
        builder.setMessage(message)
        builder.setNegativeButton(buttonText) { dialogInterface, _ ->
            function(dialogInterface)
        }
        builder.setCancelable(cancelable)
        val dialog = builder.create()

        dialog.show()
    }catch (e : Exception){
//        FirebaseCrashlytics.getInstance().recordException(e)
    }
}

fun Activity.alert(title: String, message: String, function: (DialogInterface) -> Unit) {
    alert(title, message, "OK", true, function)
}

fun Activity.alert(message: String, cancelable: Boolean, function: (DialogInterface) -> Unit) {
    alert(null, message, "OK", cancelable, function)
}

fun Fragment.alert(message: String) {
    activity?.alert(message)
}

fun Fragment.alert(message: String, cancelable: Boolean, function: (DialogInterface) -> Unit) {
    activity?.alert(null, message, "OK", cancelable, function)
}


