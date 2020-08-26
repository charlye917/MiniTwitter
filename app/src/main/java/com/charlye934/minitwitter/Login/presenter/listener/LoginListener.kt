package com.charlye934.minitwitter.Login.presenter.listener

interface LoginListener {
    fun goToLogin()
    fun goToSignUp()
    fun goToHomeActivity()
    fun saveDataSharedPrefernces(token:String, username:String, email:String, photo:String, created:String, active:Boolean)
}