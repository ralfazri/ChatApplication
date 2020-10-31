package com.surelabs.indonesia.chatapplication.chat

import android.os.Bundle
import com.surelabs.indonesia.chatapplication.BaseActivity
import com.surelabs.indonesia.chatapplication.R
import com.surelabs.indonesia.model.DataItem
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val data = intent.getParcelableExtra<DataItem>("contact")

        supportActionBar?.apply {
            title = data?.nama
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        sendMessage.setOnClickListener {
            if (message.text.toString().isEmpty()) {
                showMessage("Tidak bisa mengirimkan pesan kosong")
            } else {

            }
        }
    }
}