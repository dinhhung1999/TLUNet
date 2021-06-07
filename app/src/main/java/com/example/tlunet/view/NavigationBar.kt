package com.example.tlunet.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.tlunet.R
import com.example.tlunet.extensions.getFont
import com.mespitech.mvpbase.extensions.dp
import com.mespitech.mvpbase.extensions.sp

class NavigationBar : RoundedFrameLayout {

    private var backIcon: Drawable? = null
    private var backIconVisible = true
    private var backIconWidth = 0
    private var backIconHeight = 0
    private var backIconPadding = 0
    private var backIconColor: Int = Color.WHITE
    private var title: String? = null
    private var titleTextColor: Int = Color.WHITE
    private var titleTextSize: Int = 0
    private var titleBold = false
    private var titleItalic = false
    private var contentInsetStart = 0

    private lateinit var backImageView: ImageView
    private lateinit var tvTitle: TextView

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        obtainResources(context, attrs)
        init()
    }


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        obtainResources(context, attrs)
        init()
    }

    private fun obtainResources(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NavigationBar)
        backIcon = typedArray.getDrawable(R.styleable.NavigationBar_backIcon)
        backIconVisible = typedArray.getBoolean(R.styleable.NavigationBar_backIconVisible, true)
        backIconWidth = typedArray.getDimensionPixelSize(
            R.styleable.NavigationBar_backIconWidth,
            context.dp(24f)
        )
        backIconHeight = typedArray.getDimensionPixelSize(
            R.styleable.NavigationBar_backIconHeight,
            context.dp(24f)
        )
        backIconColor = typedArray.getColor(R.styleable.NavigationBar_backIconColor, Color.WHITE)
        title = typedArray.getString(R.styleable.NavigationBar_title)
        titleTextColor = typedArray.getColor(R.styleable.NavigationBar_titleTextColor, Color.WHITE)
        titleTextSize = typedArray.getDimensionPixelSize(
            R.styleable.NavigationBar_titleTextSize,
            context.sp(16f)
        )
        titleBold = typedArray.getBoolean(R.styleable.NavigationBar_titleBold, false)
        titleItalic = typedArray.getBoolean(R.styleable.NavigationBar_titleItalic, false)
        contentInsetStart = typedArray.getDimensionPixelSize(
            R.styleable.NavigationBar_contentInsetStart,
            context.dp(10f)
        )
        typedArray.recycle()
    }

    private fun init() {
        addBackIcon()
        addTitle()
    }

    private fun addBackIcon() {
        backImageView = ImageView(context)
        val layoutParams = LayoutParams(backIconWidth, backIconHeight)
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        layoutParams.marginStart = contentInsetStart
        backImageView.layoutParams = layoutParams
        backIconPadding = (backIconWidth - context.dp(24f)) / 2
        backImageView.setPadding(backIconPadding, backIconPadding, backIconPadding, backIconPadding)
        if (backIcon != null) {
            backImageView.setImageDrawable(backIcon)
        }
        backImageView.visibility = if (backIconVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
        backImageView.imageTintList = ColorStateList.valueOf(backIconColor)
        addView(backImageView)
    }

    private fun addTitle() {
        tvTitle = TextView(context)
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.marginStart = 10
        tvTitle.layoutParams = layoutParams
        tvTitle.text = if (title == null) {
            ""
        } else {
            title
        }
        tvTitle.setTextColor(titleTextColor)
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())

        val font = context.getFont(R.font.svn_gilroy_bold)
        if (titleBold && titleItalic) {
            tvTitle.setTypeface(font, Typeface.BOLD_ITALIC)
        } else if (titleBold) {
            tvTitle.setTypeface(font, Typeface.BOLD)
        } else if (titleItalic) {
            tvTitle.setTypeface(font, Typeface.ITALIC)
        }
        tvTitle.typeface = font
        addView(tvTitle)
    }

    fun setBackPressListener(f: () -> Unit) {
        backImageView.setOnClickListener {
            f()
        }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }


}