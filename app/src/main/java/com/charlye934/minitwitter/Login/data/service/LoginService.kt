package com.charlye934.minitwitter.Login.data.service

import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth
import retrofit2.http.Body
import retrofit2.http.POST

interface MiniTwitterService {
    @POST("/auth/login")
    suspend fun doLogin(@Body requestLogin:RequestLogin): ResponseAuth

    @POST("/auth/signup")
    suspend fun doSignUp(@Body requestSignUp: RequestSignUp): ResponseAuth
}