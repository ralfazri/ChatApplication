package com.surelabs.indonesia.chatapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralResponse(

    @field:SerializedName("message")
    var message: String? = null,
    @field:SerializedName("code")
    var code: Int? = null

) : Parcelable
