package com.example.tlunet.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.tlunet.R
import com.example.tlunet.extensions.dp
import kotlinx.android.synthetic.main.item_menu.view.*


class MenuItemView : FrameLayout {

    private var icon: Drawable? = null
    private var iconWidth = 0
    private var iconHeight: Int = 0
    private var title: String? = null
    private var enableSwitch = false

    private var selectIconEnd: Int = 0


    private lateinit var imageView: ImageView
    private lateinit var imageViewDown: ImageView
    private lateinit var imageViewNext: ImageView
    private lateinit var textView: TextView
    private lateinit var switch: Switch

    private var dividerColor: Int = Color.LTGRAY
    private var dividerTop = true
    private var dividerBottom = true
    private lateinit var dividerTopView: View
    private lateinit var dividerBottomView: View


    private var onSwitchChangeListener: ((Boolean) -> Unit)? = null


    constructor(context: Context) : super(context) {}

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

    private fun init(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MenuItemView)
        if (ta.hasValue(R.styleable.MenuItemView_icon)) {
            icon = ta.getDrawable(R.styleable.MenuItemView_icon)
        }
        iconWidth = ta.getDimensionPixelSize(R.styleable.MenuItemView_iconWidth, context.dp(0f))
        iconHeight = ta.getDimensionPixelSize(R.styleable.MenuItemView_iconHeight, context.dp(0f))
        title = if (ta.hasValue(R.styleable.MenuItemView_title)) {
            ta.getString(R.styleable.MenuItemView_title)
        } else {
            ""
        }
        selectIconEnd = ta.getInt(R.styleable.MenuItemView_iconEnd, 0)
        enableSwitch = ta.getBoolean(R.styleable.MenuItemView_enableSwitch, false)
        dividerColor = ta.getColor(R.styleable.MenuItemView_dividerColor, Color.LTGRAY)
        dividerTop = ta.getBoolean(R.styleable.MenuValueItemView_dividerTop, true)
        dividerBottom = ta.getBoolean(R.styleable.MenuValueItemView_dividerBottom, true)
        ta.recycle()

        initView()

    }

    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, this, false)
        imageView = view.imgIcon
        imageViewDown = view.imgDown
        imageViewNext = view.imgNext
        textView = view.tvName
        switch = view.sw

        if (icon != null) {
            imageView.setImageDrawable(icon)
        } else {
            imageView.visibility = View.GONE
        }
        setImageSize()
        when(selectIconEnd){
            1-> {imageViewDown.visibility = View.VISIBLE}
            0-> {imageViewNext.visibility = View.VISIBLE}
        }

        textView.text = title
        if (enableSwitch) {
            switch.visibility = View.VISIBLE
            view.imgNext.visibility = View.GONE
        } else {
            switch.visibility = View.GONE
        }


        switch.setOnCheckedChangeListener { _, isChecked ->
            run {
                this.onSwitchChangeListener?.invoke(isChecked)
            }
        }


        addView(view)

        dividerTopView = addDivider(Gravity.TOP)
        dividerBottomView = addDivider(Gravity.BOTTOM)

        setDividerTop(dividerTop)
        setDividerBottom(dividerBottom)
        setTitle(title?:"")
    }

    fun setOnSwitchChangeOn(onSwitchChangeOn: Boolean) {
        this.switch.isChecked = onSwitchChangeOn
    }

    fun getOnSwitchChangeOn(): Boolean {
        return this.switch.isChecked
    }

    fun setOnSwitchChangeListener(onSwitchChangeListener: ((Boolean) -> Unit)?) {
        this.onSwitchChangeListener = onSwitchChangeListener
    }

    private fun addDivider(gravity: Int): View {
        val imageView = ImageView(context)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, context.dp(0.5f))
        layoutParams.gravity = gravity
        imageView.layoutParams = layoutParams
        imageView.setBackgroundColor(dividerColor)
        addView(imageView)
        return imageView
    }

    fun setDividerTop(visible: Boolean) {
        this.dividerTop = visible
        dividerTopView.visibility = if (dividerTop) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun setDividerBottom(visible: Boolean) {
        this.dividerBottom = visible
        dividerBottomView.visibility = if (dividerBottom) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    fun setTitle(title: String){
        this.title
        textView.text = title
    }

    fun setImageSize(){
        if (iconHeight != 0 && iconWidth != 0){
            imageView.layoutParams.height = iconHeight
            imageView.layoutParams.width = iconWidth
        }
    }
}
