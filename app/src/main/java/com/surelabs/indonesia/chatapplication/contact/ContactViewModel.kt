package com.surelabs.indonesia.chatapplication.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surelabs.indonesia.model.ResponseContact
import com.surelabs.indonesia.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ContactViewModel : ViewModel() {
    private val api = NetworkModule.getService()
    var result = MutableLiveData<ResponseContact>()
    var error = MutableLiveData<String>()

    fun getContactList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = api.getUserList()
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

}