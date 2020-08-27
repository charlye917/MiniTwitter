package com.charlye934.minitwitter.Login.data.repository

import com.charlye934.minitwitter.Login.data.db.SaveDataSharedPreference
import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.common.MiniTwitterClient

class LoginRepositoryImp : LoginRepository{

    private var loginService = MiniTwitterClient.getRetrofit()
    private var dataSharedPreferences = SaveDataSharedPreference()

    override suspend fun getLogin(requestLogin: RequestLogin) = loginService.doLogin(requestLogin)
    override suspend fun doSignUp(requestSignUp: RequestSignUp) = loginService.doSignUp(requestSignUp)
    override fun saveDataShared(
        token: String,
        username: String,
        email: String,
        photo: String,
        created: String,
        active: Boolean
    ) {
        dataSharedPreferences.invoke(token,username,email,photo, created, active)
    }
}