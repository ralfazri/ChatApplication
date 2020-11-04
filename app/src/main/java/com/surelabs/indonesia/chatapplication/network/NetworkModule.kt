package com.surelabs.indonesia.chatapplication.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {

    const val BASE_URL = "http://192.168.1.102/ci-chat/index.php/Chatapi/"

    //    const val BASE_URL = "https://koreancorners.com/api/"
    private val BASEURLFCM = "https://fcm.googleapis.com/"

    private fun getOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    private fun getRetrofitFCM(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURLFCM)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

    fun fcmPush(): ApiService {
        return getRetrofitFCM().create(ApiService::class.java)
    }
}