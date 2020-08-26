package com.charlye934.minitwitter.Login.domain

import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth
import com.charlye934.minitwitter.Login.data.repository.LoginRepository
import com.charlye934.minitwitter.Login.data.repository.LoginRepositoryImp

class LoginInteractorImp : LoginInteractor{

    private var loginRepository: LoginRepository = LoginRepositoryImp()

    override suspend fun getLogin(requestLogin: RequestLogin): ResponseAuth {
        return loginRepository.getLogin(requestLogin)
    }

    override suspend fun doSignUp(requestSignUp: RequestSignUp): ResponseAuth {
        return loginRepository.doSignUp(requestSignUp)
    }

}