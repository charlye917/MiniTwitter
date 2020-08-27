package com.charlye934.minitwitter.common

import com.charlye934.minitwitter.Login.data.service.MiniTwitterService
import com.charlye934.minitwitter.home.data.service.AuthInterceptor
import com.charlye934.minitwitter.home.data.service.AuthTwitterService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MiniTwitterClient {
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitClient: Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getRetrofit() = retrofit.create(MiniTwitterService::class.java)

    fun authTwitterClient() = retrofitClient.create(AuthTwitterService::class.java)
}