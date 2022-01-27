package com.mohammedmorse.utils.extensions

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.halanchallenge.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout

fun RecyclerView.animateExtendedFab(extendedFab: ExtendedFloatingActionButton?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                extendedFab?.shrink()
            } else {
                extendedFab?.extend()
            }
        }
    }
}

fun View?.animateView(animationRes: Int): View? {
    this?.animation = AnimationUtils.loadAnimation(this?.context, animationRes)
    return this
}

fun View?.setUpListener(listerner: Animation.AnimationListener? = null): View? {
    this?.animation?.setAnimationListener(listerner)
    return this
}

fun View?.setInterpolator(inter: Interpolator? = null): View? {
    this?.animation?.interpolator = inter
    return this
}

fun View?.run() = this?.animation?.startNow()

fun EditText.intOrZero(): Int {
    val s = text.toString()
    return if (s.isEmpty()) 0 else s.toInt()
}

fun TabLayout.setOnChanged(function: (pos: Int) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {
        }

        override fun onTabSelected(p0: TabLayout.Tab) {
            function.invoke(p0.position)
        }
    })
}

fun EditText.sub(delay: Long? = 300, runnable: Runnable) {
    addTextChangedListener(MyWatcher(runnable, delay))
}

class MyWatcher(private val runnable: Runnable, private val delay: Long? = 300) : TextWatcher {
    private val handlerThread = Handler(Looper.getMainLooper())
    override fun afterTextChanged(s: Editable?) {
        handlerThread.removeCallbacks(runnable)
        handlerThread.postDelayed(runnable, delay ?: 10)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}

fun String?.isNotShort(min: Int) = this?.length ?: 0 > min

fun android.view.View.showKeyBoard(context: Context) =
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager)
        .showSoftInput(this, 0)

fun android.view.View.hideKeyBoard(context: Context) =
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager)
        .hideSoftInputFromWindow(this.windowToken, 0)


fun View.manageVisibilty(isVisible: Boolean) {
    if (isVisible) {
        showVisibilty()
    } else {
        hideVisibilty()
    }
}

fun View.hideVisibilty() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.showVisibilty() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun TextView.setTextNullable(text: String?, layout: ConstraintLayout) {
    if (TextUtils.isEmpty(text)) {
        layout.hideVisibilty()
    } else {
        layout.showVisibilty()
        setText(text)
    }
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.isGone() = this.visibility == View.GONE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    if (isVisible()) {
        this.visibility = View.GONE
    }
}

fun ViewStub.visibleIf(boolean: Boolean) = if (boolean) this.visible() else gone()

fun View.visibleIf(boolean: Boolean) = if (boolean) visible() else gone()

fun View.visibleIf(boolean: Boolean?) = if (boolean == true) visible() else gone()

fun View.enableIf(boolean: Boolean) = if (boolean) enable() else disable()

fun View.visibleOrInvIf(boolean: Boolean) = if (boolean) visible() else invisible()

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

fun android.widget.TextView.visibleIf(value: String?) {
    if (!TextUtils.isEmpty(value)) {
        this.visible()
        this.text = value
    } else this.gone()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)
