package com.surelabs.indonesia.chatapplication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surelabs.indonesia.model.GeneralResponse
import com.surelabs.indonesia.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel : ViewModel() {

    private val api = NetworkModule.getService()
    var result = MutableLiveData<GeneralResponse>()
    var error = MutableLiveData<String>()

    fun register(mUserModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = api.getPost(mUserModel)
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