package com.surelabs.indonesia.chatapplication

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    fun showMessage(message: String?) {
        Snackbar.make(findViewById(android.R.id.content), "$message", Snackbar.LENGTH_SHORT)
            .show()
    }

}