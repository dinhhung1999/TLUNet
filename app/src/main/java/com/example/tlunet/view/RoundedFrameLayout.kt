package com.example.tlunet.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.example.tlunet.R

open class RoundedFrameLayout : FrameLayout {
    private var radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

    private var bgColor = Color.WHITE

    constructor(context: Context) : super(context) {init(context, null)}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {init(context, attrs)}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            obtain(context, attrs)
        }

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

    private fun obtain(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundedFrameLayout)
        bgColor = ta.getColor(R.styleable.RoundedFrameLayout_backgroundColor, Color.WHITE)
        if (ta.hasValue(R.styleable.RoundedFrameLayout_radius)) {
            val radius = ta.getDimensionPixelSize(R.styleable.RoundedFrameLayout_radius, 0)
            for (i in radii.indices) {
                radii[i] = radius.toFloat()
            }
        } else {
            val tl = ta.getDimensionPixelSize(R.styleable.RoundedFrameLayout_radiusTopLeft, 0).toFloat()
            val tr = ta.getDimensionPixelSize(R.styleable.RoundedFrameLayout_radiusTopRight, 0).toFloat()
            val br = ta.getDimensionPixelSize(R.styleable.RoundedFrameLayout_radiusBottomRight, 0).toFloat()
            val bl = ta.getDimensionPixelSize(R.styleable.RoundedFrameLayout_radiusBottomLeft, 0).toFloat()
            radii = floatArrayOf(tl, tl, tr, tr, br, br, bl, bl)
        }
        ta.recycle()
    }
}