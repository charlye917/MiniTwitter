package com.charlye934.minitwitter.Login.data.repository

import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth
import com.charlye934.minitwitter.Login.data.service.MiniTwitterClient

class LoginRepositoryImp : LoginRepository{

    private var loginService = MiniTwitterClient.getRetrofit()

    override suspend fun getLogin(requestLogin: RequestLogin) = loginService.doLogin(requestLogin)
    override suspend fun doSignUp(requestSignUp: RequestSignUp) = loginService.doSignUp(requestSignUp)
}