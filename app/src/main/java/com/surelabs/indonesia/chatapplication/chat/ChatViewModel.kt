package com.surelabs.indonesia.chatapplication.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surelabs.indonesia.chatapplication.model.ChatHeader
import com.surelabs.indonesia.chatapplication.model.FCMModel
import com.surelabs.indonesia.chatapplication.model.GeneralResponse
import com.surelabs.indonesia.chatapplication.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ChatViewModel : ViewModel() {

    private val api = NetworkModule.getService()
    private val fcm = NetworkModule.fcmPush()
    var result = MutableLiveData<GeneralResponse>()
    var error = MutableLiveData<String>()
    var fcmModelRes = MutableLiveData<String>()
    fun sendMessage(chatHeader: ChatHeader) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = api.postChat(chatHeader)
                result.postValue(res)
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                when (throwable) {
                    is HttpException -> error.postValue(throwable.message())
                    is IOException -> error.postValue(throwable.message)
                    else -> {
                        error.postValue(throwable.message)
                    }
                }
            }
        }
    }

    fun sendPushNotification(mFCMModel: FCMModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = fcm.actionSendService(mFCMModel)
                fcmModelRes.postValue(res.string())
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                when (throwable) {
                    is HttpException -> error.postValue(throwable.message())
                    is IOException -> error.postValue(throwable.message)
                    else -> {
                        error.postValue(throwable.message)
                    }
                }
            }
        }
    }
}