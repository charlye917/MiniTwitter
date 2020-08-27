package com.charlye934.minitwitter.Login.domain

import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth

interface LoginInteractor {
    suspend fun getLogin(requestLogin: RequestLogin): ResponseAuth?
    suspend fun doSignUp(requestSignUp: RequestSignUp): ResponseAuth?
    fun saveDataShared(token:String, username:String, email:String, photo:String, created:String, active:Boolean)
}