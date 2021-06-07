package com.example.tlunet.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable

import com.makeramen.roundedimageview.RoundedImageView
import com.mespitech.mvpbase.extensions.dp
import android.R.attr.radius
import android.content.res.ColorStateList
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.example.tlunet.R
import com.makeramen.roundedimageview.Corner


open class RoundedBackgroundImageView : RoundedImageView {

    private var radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        obtainResources(context, attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        obtainResources(context, attrs)
        init()
    }

    private fun obtainResources(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundedBackgroundImageView)
        if (ta.hasValue(R.styleable.RoundedBackgroundImageView_radius)) {
            val radius = ta.getDimensionPixelSize(R.styleable.RoundedBackgroundImageView_radius, 0)
            for (i in radii.indices) {
                radii[i] = radius.toFloat()
            }
        } else {
            val tl = ta.getDimensionPixelSize(R.styleable.RoundedBackgroundImageView_radiusTopLeft, 0).toFloat()
            val tr = ta.getDimensionPixelSize(R.styleable.RoundedBackgroundImageView_radiusTopRight, 0).toFloat()
            val br = ta.getDimensionPixelSize(R.styleable.RoundedBackgroundImageView_radiusBottomRight, 0).toFloat()
            val bl = ta.getDimensionPixelSize(R.styleable.RoundedBackgroundImageView_radiusBottomLeft, 0).toFloat()
            radii = floatArrayOf(tl, tl, tr, tr, br, br, bl, bl)
        }
        ta.recycle()
    }

    private fun init() {
        if (background is GradientDrawable) {
            with(background as GradientDrawable) {
                background.mutate()
                cornerRadii = radii
            }
        } else if (background is ColorDrawable) {
            val color = (background as ColorDrawable).color
            val drawable = GradientDrawable()
            drawable.color = ColorStateList.valueOf(color)
            drawable.cornerRadii = radii
            background = drawable
        }
    }
}
