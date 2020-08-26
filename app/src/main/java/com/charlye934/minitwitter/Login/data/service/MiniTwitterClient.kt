package com.charlye934.minitwitter.Login.data.service

import com.charlye934.minitwitter.common.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MiniTwitterClient {

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getRetrofit() = retrofit.create(MiniTwitterService::class.java)
}