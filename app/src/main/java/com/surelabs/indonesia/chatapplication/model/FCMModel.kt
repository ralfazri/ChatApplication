package com.surelabs.indonesia.chatapplication.model

import com.google.gson.annotations.SerializedName

class FCMModel {
    @SerializedName("to")
    var token: String? = null

    @SerializedName("data")
    var body: MessageItem? = null
}