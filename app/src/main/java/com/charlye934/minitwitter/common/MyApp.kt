package com.charlye934.minitwitter.common

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        instace = this
        super.onCreate()
    }

    companion object{
        private lateinit var instace:MyApp
        fun getInstance() = instace
        fun getContext() = instace
    }
}