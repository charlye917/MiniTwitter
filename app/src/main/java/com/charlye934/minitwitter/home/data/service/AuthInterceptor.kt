package com.charlye934.minitwitter.home.data.service

import android.util.Log
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = SharedPreferencesManager().getSomeStringValue(Constants.PREF_TOKEN)
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(request)
    }
}