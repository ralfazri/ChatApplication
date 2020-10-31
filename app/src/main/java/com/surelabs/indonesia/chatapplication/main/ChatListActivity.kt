package com.surelabs.indonesia.chatapplication.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.surelabs.indonesia.chatapplication.R
import com.surelabs.indonesia.chatapplication.contact.ContactActivity
import kotlinx.android.synthetic.main.activity_chat_list.*

class ChatListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            title = "Menu Utama"
        }

        startChat.setOnClickListener {
            Intent(this@ChatListActivity, ContactActivity::class.java).apply {
                startActivity(this)
            }

        }
    }
}