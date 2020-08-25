package com.charlye934.minitwitter.Login.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("email")
    @Expose
    val email:String,

    @SerializedName("password")
    @Expose
    val password:String
)