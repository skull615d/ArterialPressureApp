package com.github.kadehar.arterialpressureapp.base

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter

fun <T> AbsDelegationAdapter<T>.setData(data: T) {
    items = data
    notifyDataSetChanged()
}

fun RecyclerView.setAdapterAndCleanupOnDetachFromWindow(recyclerViewAdapter: RecyclerView.Adapter<*>) {
    adapter = recyclerViewAdapter
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
            adapter = null
            removeOnAttachStateChangeListener(this)
        }

        override fun onViewAttachedToWindow(v: View?) {
        }
    })
}

fun View.setThrottledClickListener(delay: Long = DEFAULT_THROTTLE_DELAY, onClick: (View) -> Unit) {
    setOnClickListener {
        throttle(delay) {
            onClick(it)
        }
    }
}

fun TextInputEditText.hideSystemKeyboard(activity: FragmentActivity) {
    setOnEditorActionListener { _, action, _ ->
        if (action == EditorInfo.IME_ACTION_DONE) {
            val imm: InputMethodManager = activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.windowToken, 0)
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

private var lastClickTimestamp = 0L
private const val DEFAULT_THROTTLE_DELAY = 200L
fun throttle(delay: Long = DEFAULT_THROTTLE_DELAY, action: () -> Unit): Boolean {
    val currentTimestamp = System.currentTimeMillis()
    val delta = currentTimestamp - lastClickTimestamp
    if (delta !in 0L..delay) {
        lastClickTimestamp = currentTimestamp
        action()
        return true
    }
    return false
}