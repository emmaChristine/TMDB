package com.demo.movies.ui.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(snackbarText: String, color: Int, timeLength: Int): Snackbar {
    val snackBar = Snackbar.make(this, snackbarText, timeLength)
    val snackbarView = snackBar.view
    snackbarView.setBackgroundColor(ContextCompat.getColor(this.context, color))

    val sbTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    sbTextView.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
    sbTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER

    val params = snackbarView.layoutParams as? FrameLayout.LayoutParams
    if(params != null) {
        params.height = FrameLayout.LayoutParams.WRAP_CONTENT
        snackbarView.layoutParams = params
    }

    snackBar.show()

    return snackBar
}