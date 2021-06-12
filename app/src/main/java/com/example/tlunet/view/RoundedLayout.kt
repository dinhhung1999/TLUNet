package com.example.tlunet.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.tlunet.R


class RoundedLayout : LinearLayout {

    private var radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

    private var bgColor = Color.WHITE

    private var strokeWidth = 0
    private var strokeColor = Color.TRANSPARENT

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            obtain(context, attrs)
        }

        setupBackground()

    }

    private fun obtain(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundedLayout)
        bgColor = ta.getColor(R.styleable.RoundedLayout_backgroundColor, Color.WHITE)
        if (ta.hasValue(R.styleable.RoundedLayout_radius)) {
            val radius = ta.getDimensionPixelSize(R.styleable.RoundedLayout_radius, 0)
            for (i in radii.indices) {
                radii[i] = radius.toFloat()
            }
        } else {
            val tl = ta.getDimensionPixelSize(R.styleable.RoundedLayout_radiusTopLeft, 0).toFloat()
            val tr = ta.getDimensionPixelSize(R.styleable.RoundedLayout_radiusTopRight, 0).toFloat()
            val br =
                ta.getDimensionPixelSize(R.styleable.RoundedLayout_radiusBottomRight, 0).toFloat()
            val bl =
                ta.getDimensionPixelSize(R.styleable.RoundedLayout_radiusBottomLeft, 0).toFloat()
            radii = floatArrayOf(tl, tl, tr, tr, br, br, bl, bl)
        }
        strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundedLayout_strokeWidth, 0)
        strokeColor = ta.getColor(R.styleable.RoundedLayout_strokeColor, Color.TRANSPARENT)
        ta.recycle()
    }

    private fun setupBackground() {
        if (background is GradientDrawable) {
            with(background as GradientDrawable) {
                this.mutate()
                this.cornerRadii = radii
                this.setStroke(strokeWidth, strokeColor)
                background = this
            }
        } else if (background is ColorDrawable) {
            with(background as ColorDrawable) {
                background.mutate()
                val color = this.color
                val drawable = GradientDrawable()
                drawable.color = ColorStateList.valueOf(color)
                drawable.cornerRadii = radii
                drawable.setStroke(strokeWidth, strokeColor)
                background = drawable
            }
        }
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        setupBackground()
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        setupBackground()
    }

    fun setStrokeWidth(width: Int) {
        this.strokeWidth = width
        setupBackground()
    }

}
