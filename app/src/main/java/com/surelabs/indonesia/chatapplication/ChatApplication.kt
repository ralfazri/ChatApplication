package com.surelabs.indonesia.chatapplication

import android.app.Application
import android.content.ContextWrapper
import com.google.firebase.FirebaseApp
import com.pixplicity.easyprefs.library.Prefs

class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        Prefs.Builder()
            .setContext(this)
            .setUseDefaultSharedPreference(true)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .build()
    }
}