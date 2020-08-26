package com.charlye934.minitwitter.Login.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.Login.data.model.RequestLogin
import com.charlye934.minitwitter.Login.data.model.RequestSignUp
import com.charlye934.minitwitter.Login.data.model.ResponseAuth
import com.charlye934.minitwitter.Login.data.repository.LoginRepository
import com.charlye934.minitwitter.Login.data.repository.LoginRepositoryImp
import com.charlye934.minitwitter.Login.domain.LoginInteractor
import com.charlye934.minitwitter.Login.domain.LoginInteractorImp
import okhttp3.Response

class LoginViewModel : ViewModel() {
    private val interactor: LoginInteractor = LoginInteractorImp()

    fun sendDataLogin(requestLogin: RequestLogin) = liveData{
        val dataUser = interactor.getLogin(requestLogin)
        emit(dataUser)
    }

    fun sedDataSignUp(requestSignUp: RequestSignUp) = liveData {
        val dataSignUp = interactor.doSignUp(requestSignUp)
        emit(dataSignUp)
    }

}