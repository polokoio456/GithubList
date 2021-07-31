package com.nie.githublist.extension

import android.view.View
import android.view.ViewTreeObserver

fun View.doOnGlobalLayoutListener(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            action()
        }
    })
}