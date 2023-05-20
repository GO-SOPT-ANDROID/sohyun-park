package org.android.go.sopt

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(message: String) {
    Snackbar.make(
        this, message, Snackbar.LENGTH_SHORT
    ).show()
}

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}