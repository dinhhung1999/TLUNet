package com.example.tlunet.extensions

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat

fun Context.getColorResource(@ColorRes id: Int): Int {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        getColor(id)
    } else {
        ContextCompat.getColor(this, id)
    }
}

//fun Context.isHardwareSupported(): Boolean {
//    val fingerprintManager = FingerprintManagerCompat.from(this)
//    return fingerprintManager.isHardwareDetected
//}

//fun Context.isFingerprintAvailable(): Boolean {
//    val fingerprintManager = FingerprintManagerCompat.from(this)
//    return fingerprintManager.hasEnrolledFingerprints()
//}

fun Context.getFont(@FontRes id: Int): Typeface? {
    if (Build.VERSION.SDK_INT > 26) {
        return resources.getFont(id)
    } else {
        return ResourcesCompat.getFont(this, id)
    }
}