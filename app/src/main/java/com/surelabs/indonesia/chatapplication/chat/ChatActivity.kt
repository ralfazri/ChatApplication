package com.surelabs.indonesia.chatapplication.chat

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pixplicity.easyprefs.library.Prefs
import com.surelabs.indonesia.chatapplication.BaseActivity
import com.surelabs.indonesia.chatapplication.R
import com.surelabs.indonesia.chatapplication.register.RegisterActivity
import com.surelabs.indonesia.chatapplication.model.ChatHeader
import com.surelabs.indonesia.chatapplication.model.DataItem
import com.surelabs.indonesia.chatapplication.model.FCMModel
import com.surelabs.indonesia.chatapplication.model.MessageItem
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity() {
    private lateinit var vm: ChatViewModel
    private val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val data = intent.getParcelableExtra<DataItem>("contact")
        vm = ViewModelProvider(this).get(ChatViewModel::class.java)

        supportActionBar?.apply {
            title = data?.nama
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        sendMessage.setOnClickListener {
            if (message.text.toString().isEmpty()) {
                showMessage("Tidak bisa mengirimkan pesan kosong")
            } else {
                prepareSendMessage(message.text.toString(), data)
            }
        }
    }

    private fun prepareSendMessage(message: String, data: DataItem?) {
        val chatHeader = ChatHeader()
        val userId = Prefs.getString(RegisterActivity.USER_ID, null).replace("\"", "").toLong()

        val chatItem = MessageItem()
        chatItem.message = message
        chatItem.user_id_dest = data?.id?.toLong()
        chatItem.user_id = userId
        chatItem.token_dest = data?.token

        chatHeader.user_id = userId
        chatHeader.chatItem = chatItem

        vm.sendMessage(chatHeader)
        vm.result.observe(this) {
            logDebug(it.message, TAG)
            val fcmModel = FCMModel()
            fcmModel.token = data?.token
            fcmModel.body = chatItem
            sendPushNotification(fcmModel)
        }

        vm.error.observe(this) {
            showMessage(it)
        }

    }

    private fun sendPushNotification(fcmModel: FCMModel) {
        vm.sendPushNotification(fcmModel)
        vm.fcmModelRes.observe(this) {
            logDebug(it, TAG)
        }
        vm.fcmModelRes.observe(this) {
            logError(it, TAG)
        }
    }
}