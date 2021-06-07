package com.mespitech.mvpbase.uikit

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.SupportMenuInflater
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

import com.google.android.material.navigation.NavigationView
import com.mespitech.mvpbase.R
import com.mespitech.mvpbase.extensions.dp

class TabBarViewPager : LinearLayout {

    private val viewPager: LockableViewPager
    private val tabBarLayout: LinearLayout
    private var tabBarHeight = 0
    private var tabBarBackground: Drawable? = null
    private var activeTintColor = Color.WHITE
    private var inactiveTintColor = Color.GRAY
    private var titleTextStyle = 0
    private var activeBackgroundColor = Color.WHITE
    private var inactiveBackgroundColor = Color.GRAY
    private var tabBarPaddingTop = 0
    private var tabBarPaddingBottom = 0
    private var iconSize: Int? = null
    private var spacing = 0

    private val menuInflater: MenuInflater
    private var menu: TabBarMenu? = null

    private var onTabBarClickListener: ((Int) -> Unit)? = null

    private val tabBarItems = mutableListOf<TabBarItem>()

    private var currentIndex = 0

    private lateinit var tabBarAdapter: TabBarAdapter

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

    init {
        menuInflater = MenuInflater(context)
        viewPager = LockableViewPager(context)
        viewPager.id = R.id.vpTabBar
        tabBarLayout = LinearLayout(context)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            this.obtainResource(context, attrs)
        }
        setupSelf()
    }

    private fun obtainResource(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TabBarViewPager)
        tabBarHeight = ta.getDimensionPixelSize(R.styleable.TabBarViewPager_tabBarHeight, 0)
        tabBarBackground = ta.getDrawable(R.styleable.TabBarViewPager_tabBarBackground)
        activeTintColor = ta.getColor(R.styleable.TabBarViewPager_activeTintColor, Color.WHITE)
        inactiveTintColor = ta.getColor(R.styleable.TabBarViewPager_inactiveTintColor, Color.GRAY)

        if (ta.hasValue(R.styleable.TabBarViewPager_titleStyle)) {
            titleTextStyle = ta.getInt(R.styleable.TabBarViewPager_titleStyle, 0)
        }

        tabBarPaddingTop =
            ta.getDimensionPixelSize(R.styleable.TabBarViewPager_tabBarPaddingTop, context.dp(5f))
        tabBarPaddingBottom = ta.getDimensionPixelSize(
            R.styleable.TabBarViewPager_tabBarPaddingBottom,
            context.dp(5f)
        )

        iconSize = ta.getDimensionPixelSize(R.styleable.TabBarViewPager_iconSize, context.dp(20f))

        spacing = ta.getDimensionPixelSize(R.styleable.TabBarViewPager_spacing, context.dp(5f))

        if (ta.hasValue(R.styleable.TabBarViewPager_menu)) {
            this.inflateMenu(ta.getResourceId(R.styleable.TabBarViewPager_menu, 0))
        }
        ta.recycle()
    }

    private fun inflateMenu(menuResId: Int) {
        this.menu = TabBarMenu(context)
        menuInflater.inflate(menuResId, this.menu)
        val itemCount = this.menu!!.size()
        for (i in 0 until itemCount) {
            val menuItem = this.menu!!.getItem(i)
            this.addMenuItem(menuItem, i)
        }
    }

    private fun addMenuItem(menuItem: MenuItem, index: Int) {
        val icon = menuItem.icon
        val title = menuItem.title
        val imageView = addIcon(icon)
        val textView = addTitle(title)
        val container = createContainer(imageView, textView)
        val tabBarItem = TabBarItem(container, imageView, textView)
        this.tabBarItems.add(tabBarItem)
        tabBarLayout.addView(container)
        container.setOnClickListener {
            if (currentIndex != index) {
                setCurrentIndex(index)
                onTabBarClickListener?.invoke(index)
            }
        }
    }

    private fun addIcon(icon: Drawable): ImageView {
        val imageView = ImageView(context)
        val size = iconSize ?: context.dp(20f)
        val layoutParams = LayoutParams(size, size)
        layoutParams.bottomMargin = spacing
        imageView.layoutParams = layoutParams
        imageView.setImageDrawable(icon)
        return imageView
    }

    private fun addTitle(title: CharSequence): TextView {
        val textView = TextView(context)
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        textView.layoutParams = layoutParams
        textView.text = title
        textView.setTypeface(
            null, when (titleTextStyle) {
                1 -> Typeface.BOLD
                2 -> Typeface.ITALIC
                3 -> Typeface.BOLD_ITALIC
                else -> Typeface.NORMAL
            }
        )
        return textView
    }

    private fun createContainer(imageView: ImageView, title: TextView): LinearLayout {
        val layout = LinearLayout(context)
        val params = LayoutParams(0, LayoutParams.MATCH_PARENT)
        params.weight = 1f
        layout.layoutParams = params
        layout.orientation = VERTICAL
        layout.addView(imageView)
        layout.addView(title)
        layout.gravity = Gravity.CENTER
        layout.setPadding(0, tabBarPaddingTop, 0, tabBarPaddingBottom)
        return layout
    }

    private fun setupTabBar() {
        val heightParams = when (tabBarHeight) {
            0 -> LayoutParams.WRAP_CONTENT
            else -> tabBarHeight
        }
        val params = LayoutParams(LayoutParams.MATCH_PARENT, heightParams)
        tabBarLayout.layoutParams = params
        tabBarLayout.background = tabBarBackground
        addView(tabBarLayout)
    }

    private fun setupViewPager() {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 0)
        params.weight = 1f
        viewPager.layoutParams = params
        addView(viewPager)
    }

    private fun setupSelf() {
        orientation = VERTICAL
        setupViewPager()
        setupTabBar()
        setCurrentIndex(0)
    }

    private fun setCurrentIndex(newIndex: Int) {
        this.currentIndex = newIndex
        this.tabBarItems.forEachIndexed { index, tabBarItem ->
            makeActive(
                tabBarItem,
                index == currentIndex
            )
        }
        if (::tabBarAdapter.isInitialized && newIndex < tabBarAdapter.count) {
            viewPager.setCurrentItem(currentIndex, false)
        }
    }

    private fun makeActive(tabBarItem: TabBarItem, active: Boolean) {
        if (active) {
            tabBarItem.container.setBackgroundColor(activeBackgroundColor)
            tabBarItem.icon.imageTintList = ColorStateList.valueOf(activeTintColor)
            tabBarItem.title.setTextColor(activeTintColor)
        } else {
            tabBarItem.container.setBackgroundColor(inactiveBackgroundColor)
            tabBarItem.icon.imageTintList = ColorStateList.valueOf(inactiveTintColor)
            tabBarItem.title.setTextColor(inactiveTintColor)
        }
    }

    fun setOnTabBarClickListener(onTabBarClickListener: ((Int) -> Unit)) {
        this.onTabBarClickListener = onTabBarClickListener
    }

    fun initAdapter(fm: FragmentManager, fragments: MutableList<Fragment>) {
        tabBarAdapter = TabBarAdapter(fm)
        tabBarAdapter.addItems(fragments)
        viewPager.adapter = tabBarAdapter
    }

    fun setOnPageChangedListener(listener: (Fragment) -> Unit) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val fragment = tabBarAdapter.getItem(position)
                listener(fragment)
            }

        })
    }

}
