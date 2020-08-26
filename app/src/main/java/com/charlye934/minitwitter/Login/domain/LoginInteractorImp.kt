package com.charlye934.minitwitter.Login.domain

import android.util.Log
import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth
import com.charlye934.minitwitter.Login.data.repository.LoginRepository
import com.charlye934.minitwitter.Login.data.repository.LoginRepositoryImp

class LoginInteractorImp : LoginInteractor{

    private val loginRepository:LoginRepository = LoginRepositoryImp()

    override suspend fun getLogin(requestLogin: RequestLogin): Pair<ResponseAuth?, Int> {
        return try {
            val response = loginRepository.getLogin(requestLogin)
            Pair(response, 200)
        }catch (e: Throwable){
            Pair(null, 400)
        }
    }

    override suspend fun doSignUp(requestSignUp: RequestSignUp): Pair<ResponseAuth?, Int> {
        return try {
            val response = loginRepository.doSignUp(requestSignUp)
            Pair(response, 200)
        }catch (e: Throwable){
            Pair(null, 400)
        }
    }

}