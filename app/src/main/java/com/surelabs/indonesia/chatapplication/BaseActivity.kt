package com.surelabs.indonesia.chatapplication

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    fun showMessage(message: String?) {
        Snackbar.make(findViewById(android.R.id.content), "$message", Snackbar.LENGTH_SHORT)
            .show()
    }

    fun logError(message: String?, tag: String? = BaseActivity::class.java.simpleName) {
        Log.e(tag, message.toString())
    }

    fun logDebug(message: String?, tag: String? = BaseActivity::class.java.simpleName) {
        Log.d(tag, message.toString())
    }

    fun logWarning(message: String?, tag: String? = BaseActivity::class.java.simpleName) {
        Log.w(tag, message.toString())
    }

}