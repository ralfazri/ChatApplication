package com.surelabs.indonesia.chatapplication.network

import com.surelabs.indonesia.chatapplication.register.UserModel
import com.surelabs.indonesia.chatapplication.model.ChatHeader
import com.surelabs.indonesia.chatapplication.model.FCMModel
import com.surelabs.indonesia.chatapplication.model.GeneralResponse
import com.surelabs.indonesia.chatapplication.model.ResponseContact
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun getPost(@Body mUserModel: UserModel): GeneralResponse

    @GET("getUserList")
    suspend fun getUserList(): ResponseContact

    @Headers(
        "Content-Type:application/json",
        "Authorization:key=AAAA47afN-M:APA91bGjZHv37MN_QYWznKgLO1d8OVCG-b_Q5C2yhWmiXYmass12-fjsdcjKVTcJAq1MY90tbqsqUNxzdzx8Ac12mhZRsrvuh9nwyk9QPEoaYNcQxmrpfAEA5LsrA4tB9qPauSMLzZnW"
    )
    @POST("fcm/send")
    suspend fun actionSendService(@Body notificationService: FCMModel): ResponseBody

    @POST("postChat")
    suspend fun postChat(@Body chatHeader: ChatHeader): GeneralResponse

}
