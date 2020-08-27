package com.charlye934.minitwitter.Login.domain

import android.util.Log
import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth
import com.charlye934.minitwitter.Login.data.repository.LoginRepository
import com.charlye934.minitwitter.Login.data.repository.LoginRepositoryImp

class LoginInteractorImp : LoginInteractor{

    private val loginRepository:LoginRepository = LoginRepositoryImp()

    override suspend fun getLogin(requestLogin: RequestLogin): ResponseAuth? {
        return try {
            val response = loginRepository.getLogin(requestLogin)
            response
        }catch (e: Throwable){
            null
        }
    }

    override suspend fun doSignUp(requestSignUp: RequestSignUp): ResponseAuth?{
        return try {
            val response = loginRepository.doSignUp(requestSignUp)
            response
        }catch (e: Throwable){
            null
        }
    }

    override fun saveDataShared(
        token: String,
        username: String,
        email: String,
        photo: String,
        created: String,
        active: Boolean
    ) {
        loginRepository.saveDataShared(token, username, email, photo, created, active)
    }


}