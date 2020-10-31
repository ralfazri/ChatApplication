package com.surelabs.indonesia.network

import com.surelabs.indonesia.chatapplication.register.UserModel
import com.surelabs.indonesia.model.GeneralResponse
import com.surelabs.indonesia.model.ResponseContact
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun getPost(@Body mUserModel: UserModel): GeneralResponse

    @GET("getUserList")
    suspend fun getUserList(): ResponseContact
}
