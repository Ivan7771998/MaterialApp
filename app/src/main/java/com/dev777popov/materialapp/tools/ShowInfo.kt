package com.dev777popov.materialapp.tools

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object ShowInfo {
    fun toast(context: Context, info: String) {
        Toast.makeText(
            context,
            info, Toast.LENGTH_SHORT
        ).show()
    }

    fun snack(view: View, info: String) {
        Snackbar.make(view, info, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}