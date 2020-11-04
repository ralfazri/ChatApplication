package com.surelabs.indonesia.chatapplication.model

data class MessageItem(
    var message: String? = null,
    var user_id: Long? = null,
    var user_id_dest: Long? = null,
    var token_dest: String? = null,
    private var _id_chat: Long? = ChatHeader()._id
)

data class ChatHeader(
    var _id: Long? = System.currentTimeMillis(),
    var user_id: Long? = null,
    var chatItem: MessageItem? = null
)